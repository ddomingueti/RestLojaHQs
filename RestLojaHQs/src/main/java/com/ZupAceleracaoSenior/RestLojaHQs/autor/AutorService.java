package com.ZupAceleracaoSenior.RestLojaHQs.autor;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository repositorioAutor;

    public Autor selecionarAutor(int id) {
        return repositorioAutor.findById(id).get();
    }

    public void salvarAutor(Autor autor) {
        repositorioAutor.save(autor);
    }

    public void deletarAutor(int id) {
        repositorioAutor.deleteById(id);
    }
}
