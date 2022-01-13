package com.ZupAceleracaoSenior.RestLojaHQs.preco;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrecoService {
    
    @Autowired
    private PrecoRepository repositorioPreco;

    public void salvarPreco(Preco preco, int comic_id) {
        repositorioPreco.save(preco);
    }

    public Preco selecionarPreco(int id) {
        return repositorioPreco.findById(id).get();
    }
}
