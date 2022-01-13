package com.ZupAceleracaoSenior.RestLojaHQs.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCpf(String cpf);
    Usuario findByEmail(String email);
    Usuario findByCpfAndEmail(String cpf, String email);
    
    @Query(value = "SELECT new Usuario (e.id, e.cpf, e.email, e.nome, e.dataNascimento) FROM Usuario e")
    List<Usuario> findAllSimples();

}
