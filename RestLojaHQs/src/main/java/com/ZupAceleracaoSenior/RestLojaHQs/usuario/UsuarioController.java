package com.ZupAceleracaoSenior.RestLojaHQs.usuario;
import com.ZupAceleracaoSenior.RestLojaHQs.Exceptions.DadosUtilizadosEx;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService servico;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) 
    public ResponseEntity<Usuario> selecionarUsuario(@PathVariable(value = "id") Integer id) {
        Usuario usuario = servico.selecionarUsuario(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarTodos() {
        return new ResponseEntity<List<Usuario>>(servico.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/cadastrados")
    public ResponseEntity<List<Usuario>> listarCadastrados() {
        return new ResponseEntity<List<Usuario>>(servico.selecionarCadastrados(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> adicionarUsuario(@Valid Usuario novoUsuario, BindingResult br) throws DadosUtilizadosEx {
        if (servico.dadosExistentes(novoUsuario)) {
            throw new DadosUtilizadosEx("O endereço de email e/ou CPF já foi utilizado anteriormente.");
        }
        
        servico.salvarUsuario(novoUsuario);
        return new ResponseEntity<>("{mensagem: 'Novo usuário criado com sucesso.', status: " + HttpStatus.CREATED + " }", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarUsuario(@RequestBody @Valid Usuario novoEstado, @PathVariable("id") Integer id) {
        Usuario estadoAntigo = servico.selecionarUsuario(id);
        estadoAntigo = novoEstado;
        servico.salvarUsuario(estadoAntigo);
        return new ResponseEntity<String>("{mensagem: 'Dado alterado com sucesso.', status = " + HttpStatus.OK + " }", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deletarUsuario(Usuario usuario) {
        usuario = servico.selecionarUsuario(usuario.getId());
        servico.deletarUsuario(usuario.getId());
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
