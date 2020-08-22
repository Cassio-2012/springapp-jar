package com.connection.databaseconnection.security.access;

import com.connection.databaseconnection.exception.ErroAutenticacao;
import com.connection.databaseconnection.exception.SenhaInvalidaException;
import com.connection.databaseconnection.usuario.UserRepository;
import com.connection.databaseconnection.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserBaseAcess implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        Usuario usuario = repository.findByEmail(email);
        if (usuario == null) {
            throw new ErroAutenticacao("Usuário não cadastrado");
        } else {
            return User
                    .builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha())
                    .roles("USER")
                    .build();
        }
    }

}
