package com.connection.databaseconnection.conhecimento;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import com.connection.databaseconnection.associative.interesse.InteresseUsuario;
import com.connection.databaseconnection.conhecimento.stack.PilhaBusca;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import com.connection.databaseconnection.exception.ErroConexao;
import com.connection.databaseconnection.exception.KnowNotFoundException;
import com.connection.databaseconnection.exception.UserNotFoundException;
import com.connection.databaseconnection.iterators.BuscaBuilder;
import com.connection.databaseconnection.iterators.ConhecimentoBuilder;
import com.connection.databaseconnection.iterators.InteresseBuilder;
import com.connection.databaseconnection.usuario.UserService;
import com.connection.databaseconnection.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

    @Autowired
    ConhecimentoService controller;

    @Autowired
    UserService userController;

    @Autowired
    ConhecimentoBuilder conhecimentoBuilder;

    @Autowired
    InteresseBuilder interesseBuilder;

    @Autowired
    private BuscaBuilder buscaBuilder;

    PilhaBusca<TipoConhecimento> buscas = new PilhaBusca<TipoConhecimento>(20);

    @PostMapping("/adicionar/conhecimento")
    public ResponseEntity novoConhecimento(@RequestBody ConhecimentoUsuario conhecimentoUsuario) {

        try {
            Optional<Conhecimento> resultKnow = controller.buscaConhecimentoPorId
                    (conhecimentoUsuario.getConhecimento().getId_conhecimento());

            Usuario resultUser = userController.buscaporId
                    (conhecimentoUsuario.getUsuario().getId());

            ConhecimentoUsuario novoConhecimento = ConhecimentoUsuario.builder()
                    .descricao_user(conhecimentoUsuario.getDescricao_user())
                    .nivel(conhecimentoUsuario.getNivel()).usuario(resultUser)
                    .conhecimento(resultKnow.get()).build();

           boolean salvo = controller.saveConhecimentoUsuario(novoConhecimento);

           if (salvo) {
               return ResponseEntity.ok(novoConhecimento);
           }
           else {
               return ResponseEntity.status(404).build();
           }

        }catch (ErroConexao erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @PostMapping("/adicionar/interesse")
    public ResponseEntity novoInteresse(@RequestBody InteresseUsuario interesseUsuario) {

        try {
            Optional<Conhecimento> resultKnow = controller.buscaConhecimentoPorId
                    (interesseUsuario.getConhecimento().getId_conhecimento());

            Usuario resultUser = userController.buscaporId
                    (interesseUsuario.getUsuario().getId());

            InteresseUsuario novoInteresse = InteresseUsuario.builder()
                    .descricao_interesse(interesseUsuario.getDescricao_interesse())
                    .conhecimento(resultKnow.get()).usuario(resultUser).build();

            boolean salvo = controller.saveInteresseUsuario(novoInteresse);

            if (salvo) {
                return ResponseEntity.ok(novoInteresse);
            }
            else {
                return ResponseEntity.status(404).build();
            }

        }catch (ErroConexao erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @GetMapping("/buscar/conhecimentos")
    public ResponseEntity buscarConhecimentos(@RequestParam(required = true) Integer id ) {

        try {
            List<ConhecimentoUsuario> result = controller.buscaConhecimentosPerfil(id);

            return ResponseEntity.ok(conhecimentoBuilder.nextList(result));

        }catch (UserNotFoundException e) {

            return ResponseEntity.noContent().build();

        }
    }

    @GetMapping("/buscar/interesses")
    public ResponseEntity buscarInteresses(@RequestParam(required = true) Integer id ) {

        try {
            List<InteresseUsuario> result = controller.buscarInteresses(id);

            return ResponseEntity.ok(interesseBuilder.nextList(result));

        }catch (UserNotFoundException e) {

            return ResponseEntity.noContent().build();

        }

    }

    @DeleteMapping("/remover/conhecimento/{id}")
    public ResponseEntity removerConhecimentoPorId(@PathVariable("id") Long id) {

        try{
            boolean delete = controller.deleteKnowById(id);
            if(delete) {
                return ResponseEntity.ok().build();
            }

            throw new KnowNotFoundException();

        }catch (KnowNotFoundException erro) {

            return ResponseEntity.badRequest().body(erro.getMessage());

        }
    }

    @DeleteMapping("/remover/interesse/{id}")
    public ResponseEntity removerInteresseorId(@PathVariable("id") Integer id) {

        try{
            boolean delete = controller.deleteInterestById(id);
            if(delete) {
                return ResponseEntity.ok().build();
            }

            throw new KnowNotFoundException();

        }catch (KnowNotFoundException erro) {

            return ResponseEntity.badRequest().body(erro.getMessage());

        }
    }


    @GetMapping("/recomendados")
    public ResponseEntity recomendados() {
        try {
            if(buscas.isEmpty()) {
                List<Conhecimento> result = controller.recomendadosDefault();
                if (result == null) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT)
                            .body("Nenhum conhecimento na base de dados");
                }
                return ResponseEntity.ok(result);
            }

            List<Conhecimento> result = controller.recomendadosByType(buscas.pop());
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Nenhum conhecimento na base de dados");
            }

            return ResponseEntity.ok(result);

        } catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find")
    public ResponseEntity findUsers(@RequestParam(required = false) String conhecimento,
                                    @RequestParam(required = false) Integer level,
                                    @RequestParam(required = false) TipoConhecimento tipo ) {
        try {

            if(tipo != null) {
                if (level > 0) {

                    Conhecimento consulta = Conhecimento.builder().tipo(tipo).build();

                    List conhecimentos = userController.buscarPorTipoAndNivel(consulta.getTipo(), level);

                    if (conhecimentos == null) {
                        return new ResponseEntity("Infelizmente ainda não temos usuários que " +
                                "possuem este nível de conhecimento .",HttpStatus.NOT_FOUND);
                    } else {
                        buscas.push(consulta.getTipo());
                        return ResponseEntity.ok(conhecimentos);
                    }

                } else {
                    Conhecimento consulta = Conhecimento.builder().tipo(tipo).build();


                    List conhecimentos = userController.buscarPorTipo(consulta.getTipo());

                    if (conhecimentos == null) {
                        return new ResponseEntity("Infelizmente ainda não temos usuários que" +
                                " possuem este tipo de conhecimento ."
                                , HttpStatus.NOT_FOUND);
                    } else {
                        buscas.push(consulta.getTipo());
                        return ResponseEntity.ok(conhecimentos);
                    }
                }
            }
            if (level > 0) {
                if (conhecimento != null) {

                    Conhecimento con = Conhecimento.builder().conhecimento(conhecimento).build();
                    ConhecimentoUsuario consulta = ConhecimentoUsuario.builder()
                            .conhecimento(con)
                            .nivel(level).build();

                    List<ConhecimentoUsuario> conhecimentos = userController.buscarPorLevelandConhecimento(consulta);


                    if (conhecimentos == null) {
                        return new ResponseEntity("Infelizmente ainda não temos usuários que " +
                                "possuem este nível de conhecimento .",HttpStatus.NOT_FOUND);
                    } else {

                        buscas.push(conhecimentos.get(0).getConhecimento().getTipo());
                        return ResponseEntity.ok(buscaBuilder.nextList(conhecimentos));
                    }

                }else {

                    ConhecimentoUsuario consulta = ConhecimentoUsuario.builder().nivel(level).build();

                    List<ConhecimentoUsuario> conhecimentos = userController.buscarPorLevel(consulta.getNivel());


                    if (conhecimentos == null) {
                        return new ResponseEntity("Infelizmente ainda não temos usuários que possuem este nível" +
                                " de conhecimento ."
                                , HttpStatus.NOT_FOUND);
                    } else {
                        buscas.push(conhecimentos.get(0).getConhecimento().getTipo());
                        return ResponseEntity.ok(buscaBuilder.nextList(conhecimentos));
                    }
                }

            }

            Conhecimento consulta = Conhecimento.builder().conhecimento(conhecimento).build();

            List<ConhecimentoUsuario> conhecimentos = userController.buscarConhecimentos(consulta.getConhecimento());


            if (conhecimentos == null) {
                return new ResponseEntity("Infelizmente ainda não temos usuários que possuem este conhecimento ."
                        , HttpStatus.NOT_FOUND);
            }
            else{
                buscas.push(conhecimentos.get(0).getConhecimento().getTipo());
                return ResponseEntity.ok(buscaBuilder.nextList(conhecimentos));
            }


        } catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/types")
    public ResponseEntity buscaTipos() {

        try {
            List busca = controller.getListTypes();

            return ResponseEntity.ok(busca);
        }catch (ErroConexao erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @GetMapping("/knowledges/{tipo}")
    public ResponseEntity buscaTipos(@PathVariable("tipo") TipoConhecimento tipo ) {

        try {
            List busca = controller.getListKnows(tipo);

            if(busca.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(busca);
        }catch (ErroConexao erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

}
