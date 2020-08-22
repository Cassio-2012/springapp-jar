package com.connection.databaseconnection.evento;

import com.connection.databaseconnection.evento.client.cep.ClientViaCep;
import com.connection.databaseconnection.evento.convidado.Convidado;
import com.connection.databaseconnection.evento.convidado.ConvidadoRepository;
import com.connection.databaseconnection.usuario.UserService;
import com.connection.databaseconnection.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cr;
    @Autowired
    private UserService us;

    @Autowired
    private ClientViaCep viaCep;

    @PostMapping(path = "/cadastrarEvento/{idUsuario}")
    public ResponseEntity form(@RequestBody Evento evento, @PathVariable("idUsuario") Integer idUsuario) {
        try {
            if (evento == null) {
                System.out.println("Cadastro vazio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        evento.setAdm(idUsuario);
        er.save(evento);
        return ok(evento);
    }

    @GetMapping(path = "/eventos")
    public ResponseEntity listaEventos() {
        Iterable<Evento> eventos = er.findAll();
        if (eventos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ok(eventos);
        }

    }

    @GetMapping(path = "/eventos/{codigo}")
    public ResponseEntity eventosEspecificos ( @PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        return ok(evento);
    }

    @GetMapping(path = "/convidado/{codigo}")
    public ResponseEntity detalhesEvento(@PathVariable("codigo") long codigo) {
        Evento evento = er.findByCodigo(codigo);
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        if (convidados == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ok(convidados);
        }
    }
    @PostMapping(path = "/convidado/{codigo}/{idUsuario}")
    public ResponseEntity cadastroConvidado(@PathVariable("codigo") Integer codigo,
                                            @PathVariable("idUsuario") Integer idUsuario) {
        Evento evento = er.findByCodigo(codigo);
        Convidado convidado = new Convidado();

        Usuario usuario =  us.buscaporId(idUsuario);

        if (usuario.getId() == evento.getAdm()) {
            return ResponseEntity.status(403).build();
        }else {
            convidado.setNomeConvidado(usuario.getNome());
            convidado.setEmail(usuario.getEmail());
            convidado.setEvento(evento);
            cr.save(convidado);
            return ResponseEntity.ok(convidado);
        }
    }


    @DeleteMapping(path = "/evento/{codigo}/{idUsuario}")
    public ResponseEntity deletarEvento(@PathVariable("codigo") Integer codigo,
                                        @PathVariable("idUsuario") Integer idUsuario) {
        Evento evento = er.findByCodigo(codigo);
        Usuario usuario =  us.buscaporId(idUsuario);
        if (usuario.getId() == evento.getAdm()) {
            Iterable<Convidado> convidados = cr.findByEvento(evento);
            cr.deleteAll(convidados);
            er.delete(evento);
            return ok().build();

        } else {
            return ResponseEntity.status(403).build();
        }

    }

//    @DeleteMapping(path = "/convidado/{id}")
//    public ResponseEntity delete(@PathVariable String id,
//                                 @PathVariable("idUsuario") Integer idUsuario) {
//        cr.findById(id);
//        cr.deleteById(id);
//        return ok().build();
//
//    }
    @GetMapping("/cep/{cep}")
    public ResponseEntity consultarCep(@PathVariable String cep) {
        com.connection.databaseconnection.evento.Cep cepEncontrado = viaCep.getCep(cep);
        try {
            if (cepEncontrado == null) {
                System.out.println("cep vazio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(cepEncontrado);
    }
}
