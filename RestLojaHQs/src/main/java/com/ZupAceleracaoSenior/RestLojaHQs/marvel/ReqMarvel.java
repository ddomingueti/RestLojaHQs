package com.ZupAceleracaoSenior.RestLojaHQs.marvel;
import com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos.ComicResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient (name="marvel", url="http://gateway.marvel.com/v1/public")
public interface ReqMarvel  {
    
    @GetMapping("/comics/{comicId}")
    ComicResponse getComicById(@RequestParam(value="ts") Long timeStamp, 
    @RequestParam(value = "apikey") String chavePublica,
    @RequestParam(value="hash") String hash, @PathVariable(value = "comicId") int comicId);
}