package com.ZupAceleracaoSenior.RestLojaHQs.comic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ZupAceleracaoSenior.RestLojaHQs.autor.Autor;
import com.ZupAceleracaoSenior.RestLojaHQs.autor.AutorService;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ComicPrice;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ComicResponse;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.Creator;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ResultsResponse;
import com.ZupAceleracaoSenior.RestLojaHQs.preco.Preco;
import com.ZupAceleracaoSenior.RestLojaHQs.preco.PrecoService;
import com.ZupAceleracaoSenior.RestLojaHQs.usuario.Usuario;
import com.ZupAceleracaoSenior.RestLojaHQs.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/comic")
public class ComicController {

    @Autowired
    private ComicService servicoComic;
    @Autowired 
    UsuarioService servicoUsuario;
    @Autowired
    PrecoService servicoPreco;
    @Autowired 
    AutorService servicoAutor;
    
    @PostMapping("/{comic_id}/{usuario_id}")
    public ResponseEntity<Comic> adicionarComicUsuario(@PathVariable int comic_id, @PathVariable int usuario_id) {
        
        String urlRequisicaoMarvel = "http://localhost:8080/marvel/" + comic_id;
        RestTemplate restTemplate = new RestTemplate();
        ComicResponse resposta = restTemplate.getForObject(urlRequisicaoMarvel, ComicResponse.class, comic_id);
        
        Comic comic = new Comic();
        ResultsResponse resultado = resposta.getData().getResults().get(0);
        comic.setId(resultado.getId());
        comic.setDescricao(resultado.getDescription());
        comic.setIsbn(resultado.getIsbn());
        comic.setTitulo(resultado.getTitle());

        comic.setPreco(new ArrayList<Preco>());
        for (ComicPrice p : resultado.getPrices()) {
            comic.getPreco().add(new Preco(p.getType(), p.getPrice()));
        }

        for (Preco p : comic.getPreco()) {
            p.setComic(comic);
        }
        
        comic.setAutores(new HashSet<Autor>());
        if (resultado.getCreators() != null) {
            for (Creator c : resultado.getCreators().getItems()) {
                comic.getAutores().add(new Autor(c.getName(), c.getrole()));
            }
        }
        
        servicoComic.salvarComic(comic);
        
        Usuario usuario = servicoUsuario.selecionarUsuario(usuario_id);
        usuario.getListaHqs().add(comic);
        servicoUsuario.salvarUsuario(usuario);
        
        return new ResponseEntity<Comic>(comic, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comic> selecionarComic(@PathVariable("id") int id) {
        Comic comic = servicoComic.selecionarComic(id);
        return new ResponseEntity<Comic>(comic, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario_id}")
    public ResponseEntity<List<Comic>> selecionaComicsUsuario(@PathVariable("usuario_id") int usuario_id) {
        List<Comic> comics = servicoComic.selecionarPorUsuario(usuario_id);
        for (Comic c : comics) {
            if (c.verificaDesconto()) {
                c.aplicaDesconto();
            }
        }
        return new ResponseEntity<List<Comic>>(comics, HttpStatus.OK);
    }
}
