package com.ZupAceleracaoSenior.RestLojaHQs.marvel;

import com.ZupAceleracaoSenior.RestLojaHQs.Exceptions.RecursoNaoEncontradoEx;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ComicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReqMarvelController {

    @Autowired
    private ReqMarvelService servico;

    @RequestMapping(value = "/marvel/{comicId}", method = RequestMethod.GET) 
    public ResponseEntity<ComicResponse> GetComic(@PathVariable(value = "comicId") Integer comicId) throws RecursoNaoEncontradoEx {
        ComicResponse c = servico.getComic(comicId);
        if (c.getCode() == 404) {
            throw new RecursoNaoEncontradoEx("");
        }
        return new ResponseEntity<ComicResponse>(c, HttpStatus.OK);
    }
}
