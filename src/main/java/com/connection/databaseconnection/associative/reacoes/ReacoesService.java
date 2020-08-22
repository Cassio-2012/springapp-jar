package com.connection.databaseconnection.associative.reacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReacoesService {

    @Autowired
    private ReacoesRepository reacoesRepository;


    public boolean salvarReacao(Reacoes reacao) {

        reacoesRepository.save(reacao);

        return true;
    }

    public Integer validarReacao(long usuario, long post) {

        List<Reacoes> reacao = reacoesRepository.validaReacao(usuario, post);

        if(reacao.isEmpty()) {
            return 0;
        }
        return reacao.get(0).getTipo();
    }

}
