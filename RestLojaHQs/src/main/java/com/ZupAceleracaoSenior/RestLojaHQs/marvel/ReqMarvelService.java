package com.ZupAceleracaoSenior.RestLojaHQs.marvel;

import java.util.Date;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ComicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class ReqMarvelService {

    private final String chavePrivada = "c644b14a880f3576ee6b829887b1137a15597f23";
    private final String chavePublica = "6ae79afca052247528ab7e230f5260c8";
    @Autowired
    ReqMarvel cliente;

    private String construirHash(Long timeStamp) {
        String token = timeStamp + chavePrivada + chavePublica;
        return DigestUtils.md5DigestAsHex(token.getBytes());
    }

    public ComicResponse getComic(int comicId) {
        long timeStamp = new Date().getTime();
        return cliente.getComicById(timeStamp, chavePublica, construirHash(timeStamp), comicId);
    }
}
