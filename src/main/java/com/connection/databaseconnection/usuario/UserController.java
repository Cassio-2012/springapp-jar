package com.connection.databaseconnection.usuario;

import com.connection.databaseconnection.security.jwt.JwtService;
import com.connection.databaseconnection.conhecimento.ConhecimentoRepository;
import com.connection.databaseconnection.dto.TokenDTO;
import com.connection.databaseconnection.dto.UserDTO;
import com.connection.databaseconnection.dto.UsuarioViewDTO;
import com.connection.databaseconnection.exception.*;
import com.connection.databaseconnection.security.access.UserBaseAcess;
import com.connection.databaseconnection.security.captcha.CaptchaDTO;
import com.connection.databaseconnection.security.captcha.CaptchaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@Controller
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    //Esse atributo vai armazenar o usuario atual
    private Usuario currentUser = null;

    //Esse Atributo vai nos permitir utilizar os metodos da classe Service
    @Autowired
    private UserService controller;

    @Autowired
    private ConhecimentoRepository conhecimentoRepository;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Autowired
    private PasswordEncoder encoder;

    private final UserBaseAcess userBaseAcess;
//    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;




    @PostMapping("/recaptcha")
    public ResponseEntity recaptchaValidate(@RequestBody CaptchaDTO captchaDTO) throws Exception {

        Boolean isValidCaptcha = captchaValidator.validateCaptcha(captchaDTO.getCaptcha());

        if(!isValidCaptcha){
            return ResponseEntity.badRequest().body("Captcha não valido");
        }
        return ResponseEntity.ok("Captcha validado com sucesso");
    }


    @GetMapping("/logoff")
    public ResponseEntity logoff() {
        try {
            currentUser = null;
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    //Pega o json fornecido pelo front do tipo userDTO e cria um user do tipo User
    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody UserDTO userDTO) {

        Usuario user = Usuario.builder()
                .nome(userDTO.getNome())
                .email(userDTO.getEmail())
                .photo(userDTO.getPhoto())
                .senha(userDTO.getSenha())
                .sobre(userDTO.getSobre())
                .local(userDTO.getLocal())
                .title(userDTO.getTitle()).build();

        try {

            String senhaCriptografada = encoder.encode(user.getSenha());
            user.setSenha(senhaCriptografada);

            Usuario userSalvo = controller.saveUser(user);
            return new ResponseEntity(userSalvo, HttpStatus.CREATED);
        } catch (RegraException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/about")
    public ResponseEntity sobre(@RequestParam(required = true) Integer id) {
        try {


            Usuario user = this.controller.buscaporId(id);
            if (user != null) {
                return new ResponseEntity(user.getSobre(), HttpStatus.OK);
            } else {
                return new ResponseEntity("Usuário não encontrado", HttpStatus.NO_CONTENT);
            }

        } catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/about")
    public ResponseEntity atualizarSobre(@RequestBody UserDTO userDTO) {

        try {
            Usuario user = Usuario.builder().id(userDTO.getId()).sobre(userDTO.getSobre()).build();

            controller.updateSobre(user);

            return new ResponseEntity(HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/view/{id}")
    public ResponseEntity view(@PathVariable("id") Integer id) {

        try {

            UsuarioViewDTO user = this.controller.buscaViewporId(id);
            if (user != null) {
                return new ResponseEntity(user, HttpStatus.OK);
            } else {
                return new ResponseEntity("Usuário não encontrado", HttpStatus.NO_CONTENT);
            }

        } catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity autenticar(@RequestBody UserDTO credenciais) {
        try {


            Usuario usuarioAutenticado = controller.authentication(credenciais.getEmail(),credenciais.getSenha());

            String token = jwtService.gerarToken(usuarioAutenticado);

            currentUser = usuarioAutenticado;

            TokenDTO user = new TokenDTO(usuarioAutenticado.getEmail(),usuarioAutenticado.getNome(),
                    usuarioAutenticado.getPhoto(), token, usuarioAutenticado.getId());

            return ResponseEntity.ok(user);

        } catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    public Usuario getCurrentUser() {
        return this.currentUser;
    }

}
