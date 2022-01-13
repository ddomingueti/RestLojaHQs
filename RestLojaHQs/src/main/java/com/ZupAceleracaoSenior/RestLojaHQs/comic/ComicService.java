package com.ZupAceleracaoSenior.RestLojaHQs.comic;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ComicService {
    @Autowired
    private ComicRepository repositorioComic;

    public List<Comic> listarTodos() {
        return repositorioComic.findAll();
    }

    public Comic selecionarComic(Integer id) {
        return repositorioComic.findById(id).get();
    }

    public List<Comic> selecionarPorUsuario(Integer id) {
        return repositorioComic.findByUsuario(id);
    }

    public void salvarComic(Comic novaComic) {
        repositorioComic.save(novaComic);
    }

    public void deletarComic(Integer id) {
        repositorioComic.deleteById(id);
    }
}