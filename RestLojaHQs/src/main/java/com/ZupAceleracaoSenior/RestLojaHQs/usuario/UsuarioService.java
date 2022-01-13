package com.ZupAceleracaoSenior.RestLojaHQs.usuario;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository repositorioUsuario;

    public List<Usuario> listarTodos() {
        return repositorioUsuario.findAll();
    }

    public List<Usuario> selecionarCadastrados() {
        return repositorioUsuario.findAllSimples();
    }

    public Usuario selecionarUsuario(Integer id) {
        return repositorioUsuario.findById(id).get();
    }

    public Usuario selecionarCpf(String cpf) {
        return repositorioUsuario.findByCpf(cpf);
    }

    public Usuario selecionarEmail(String email) {
        return repositorioUsuario.findByEmail(email);
    }
    
    public void salvarUsuario(Usuario novoUsuario) {
        repositorioUsuario.save(novoUsuario);
    }

    public void deletarUsuario(Integer id) {
        repositorioUsuario.deleteById(id);
    }

    public boolean dadosExistentes(Usuario usuario) {
        return (repositorioUsuario.findByCpf(usuario.getCpf()) != null) || (repositorioUsuario.findByEmail(usuario.getEmail()) != null);
    }


}
