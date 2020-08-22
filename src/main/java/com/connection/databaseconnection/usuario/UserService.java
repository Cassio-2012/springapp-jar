package com.connection.databaseconnection.usuario;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuarioRepository;
import com.connection.databaseconnection.conhecimento.Conhecimento;
import com.connection.databaseconnection.conhecimento.ConhecimentoRepository;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import com.connection.databaseconnection.dto.BuscaDTO;
import com.connection.databaseconnection.dto.UsuarioViewDTO;
import com.connection.databaseconnection.exception.ErroAutenticacao;
import com.connection.databaseconnection.exception.RegraException;
import com.connection.databaseconnection.exception.UserNotFoundException;

import com.connection.databaseconnection.iterators.BuscaBuilder;
import com.connection.databaseconnection.security.access.UserBaseAcess;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BuscaBuilder buscaBuilder;

    @Autowired
    private ConhecimentoRepository conhecimentoRepository;

    @Autowired
    ConhecimentoUsuarioRepository conhecimentoUsuarioRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserBaseAcess userBaseAcess;


    public UserService(UserRepository repository) {
        super();
        this.repository = repository;
    }

    public Usuario buscaporId(Integer id) {

        Optional<Usuario> result = this.repository.findById(id);

        if(result.isPresent()) {
            return result.get();
        }
        else {
            return null;
        }

    }


    @Transactional
    public Usuario saveUser(Usuario user) {

        verifyNewEmail(user.getEmail());

        repository.save(user);
        return user;
    }


    public void verifyNewEmail(String email) {
        boolean existe = repository.existsByEmail(email);

        if (existe) {
            throw new RegraException("Já existe um usuário cadastrado com este e-mail!");
        }
    }



    public Usuario authentication(String email, String senha) {

        UserDetails userDetails = userBaseAcess.loadUserByUsername(email);
        
        if (userDetails == null) {
            throw new ErroAutenticacao("Usuário não cadastrado");
        }

        boolean senhasBatem = encoder.matches( senha, userDetails.getPassword() );

        if (senhasBatem) {
            return repository.findByEmail(email);
        }

        throw new ErroAutenticacao("Senha inválida");

    }

    @Transactional
    public void updateSobre(Usuario usuario) {

        repository.findById(usuario.getId())
                .map( user -> {
                    user.setSobre(usuario.getSobre());
                    return repository.save(user);
                }).orElseThrow(() -> new UserNotFoundException());

    }

    public List buscarConhecimentos(String conhecimento) {

        List<Conhecimento> busca = conhecimentoRepository.findByKnow(conhecimento);

        List<ConhecimentoUsuario> novaBusca = new ArrayList<>();

        if(busca.isEmpty()) {
            return null;
        }
        else {
            for(int i = 0; i < busca.size(); i++ ) {

                novaBusca.addAll(conhecimentoUsuarioRepository
                        .findByIdKnowId(busca.get(i).getId_conhecimento()));
            }
        }

        if(novaBusca.isEmpty()) {
            return null;
        }
        else{
            return novaBusca;
        }

    }

    public List<BuscaDTO> buscarPorTipo(TipoConhecimento tipo) {

        List<Conhecimento> busca = conhecimentoRepository.findByTipo(tipo);

        List<ConhecimentoUsuario> novaBusca;

        if(busca.isEmpty()) {
            return null;
        }
        else {
            novaBusca = conhecimentoUsuarioRepository
                    .findByIdKnowId(busca.get(0).getId_conhecimento());
        }

        if(novaBusca.isEmpty()) {
            return null;
        }
        else{
            return buscaBuilder.nextList(novaBusca);
        }

    }


    public UsuarioViewDTO buscaViewporId(Integer id) {

        Optional<Usuario> result = this.repository.findById(id);
        UsuarioViewDTO view ;

        if(result.isPresent()) {

            view = new UsuarioViewDTO
                    (result.get().getSobre(), result.get().getPhoto(), result.get().getId());

            return view;
        }
        else {
            return null;
        }

    }

    public List buscarPorLevel(int nivel) {

        List<ConhecimentoUsuario> busca = conhecimentoUsuarioRepository.findByLevel(nivel);

        if(busca.isEmpty()) {
            return null;
        }
        else {
            return busca;
        }

    }

    public List buscarPorLevelandConhecimento(ConhecimentoUsuario consulta) {

        List<ConhecimentoUsuario> busca = conhecimentoUsuarioRepository.findByConhecimentoandLevel
                (consulta.getNivel(), consulta.getConhecimento().getConhecimento());

        if(busca.isEmpty()) {
            return null;
        }
        else {
            return busca;
        }

    }

    public List buscarPorTipoAndNivel(TipoConhecimento type, int level) {


        List<ConhecimentoUsuario> busca;

        busca = conhecimentoUsuarioRepository
                    .findByLevelandType(level, type);

        if(busca.isEmpty()) {
            return null;
        }
        else{
            return buscaBuilder.nextList(busca);
        }

    }
}







