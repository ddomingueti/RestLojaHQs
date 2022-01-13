package com.ZupAceleracaoSenior.RestLojaHQs.Exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

public class RespostaErro {
    private String mensagem;
    private List<String> detalhes;
    private HttpStatus status;

    public RespostaErro() { }
    
    public RespostaErro(String mensagem, List<String> detalhes, HttpStatus status) {
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.status = status;
    }

    public String getMensagem() { return this.mensagem; }
    public void setMensagem(String valor) { this.mensagem = valor; }
    public List<String> getDetalhes() { return this.detalhes; }
    public void setDetalhes(List<String> valor) { this.detalhes = valor; }
    public void setStatus(HttpStatus valor) { this.status = valor; }
    public HttpStatus getStatus() { return this.status; }
}
