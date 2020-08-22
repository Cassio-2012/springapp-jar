package com.connection.databaseconnection.conhecimento;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuarioRepository;
import com.connection.databaseconnection.associative.interesse.InteresseUsuario;
import com.connection.databaseconnection.associative.interesse.InteresseUsuarioRepository;
import com.connection.databaseconnection.conhecimento.knowledge.KnowSelectList;
import com.connection.databaseconnection.conhecimento.knowledge.KnowledgeModel;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import com.connection.databaseconnection.conhecimento.types.TypeSelectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConhecimentoService {

    @Autowired
    private ConhecimentoRepository repository;

    @Autowired
    private ConhecimentoUsuarioRepository conhecimentoUsuarioRepository;

    @Autowired
    private InteresseUsuarioRepository interesseUsuarioRepository;

    @Autowired
    private EntityManager entityManager;

    public ConhecimentoService(ConhecimentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    Conhecimento adicionarNovo(Conhecimento conhecimento){
        return repository.save(conhecimento);
    }

    Conhecimento atualizar(Conhecimento conhecimento) {
        return null;
    }

    void apagar(Conhecimento conhecimento) {
        return;
    }

    public List<Conhecimento> recomendadosDefault(){

        List<Conhecimento> result =
                entityManager.createQuery(
                        "select c from Conhecimento c order by NEWID()")
                .setMaxResults(4)
                .getResultList();

        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<Conhecimento> recomendadosByType(TipoConhecimento tipo){

        List<Conhecimento> result =
                entityManager.createQuery(
                        "select c from Conhecimento c where c.tipo = :param order by NEWID()")
                        .setParameter("param", tipo)
                        .setMaxResults(4)
                        .getResultList();

        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public Optional<Conhecimento> buscaConhecimentoPorId (Long id) {

        Optional<Conhecimento> busca = repository.findById(id);

        if (busca.isPresent()) {
            return busca;
        }
        else return null;

    }

    public List<ConhecimentoUsuario> buscaConhecimentosPerfil(Integer id) {

        List<ConhecimentoUsuario>  result = conhecimentoUsuarioRepository.findConhecimentoById(id);

        return result;
    }

    public List<InteresseUsuario> buscarInteresses(Integer id) {

        List<InteresseUsuario>  result = interesseUsuarioRepository.findConhecimentoById(id);

        return result;
    }

    public boolean deleteKnowById(Long id) {

        Optional<ConhecimentoUsuario> conhecimentoUsuario = conhecimentoUsuarioRepository.findById(id);

        if (conhecimentoUsuario.isPresent()) {
            conhecimentoUsuarioRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    public boolean deleteInterestById(Integer id) {

        Optional<InteresseUsuario> interesseUsuario = interesseUsuarioRepository.findById(Long.valueOf(id));

        if (interesseUsuario.isPresent()) {
            interesseUsuarioRepository.deleteById(Long.valueOf(id));
            return true;
        }
        else return false;
    }

    public boolean saveConhecimentoUsuario(ConhecimentoUsuario novo) {

        conhecimentoUsuarioRepository.save(novo);
        return true;

    }

    public boolean saveInteresseUsuario(InteresseUsuario novo) {

        interesseUsuarioRepository.save(novo);
        return true;

    }

    public  List getListTypes() {

        TypeSelectList lista = new TypeSelectList();

        return lista.getTipos();

    }

    public List<KnowledgeModel> getListKnows(TipoConhecimento tipo) {

        KnowSelectList lista = new KnowSelectList();

        List<Conhecimento> base = repository.findByTipo(tipo);

        return lista.getConhecimentos(base);
    }


}
