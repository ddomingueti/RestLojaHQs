package com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos;

public class ComicResponse {
    private int code;
    private String status;
    private DataResponse data;


    public void setCode(int valor) { this.code = valor; }
    public void setStatus(String status) { this.status = status; }
    public void setData(DataResponse data) {this.data = data; }

    public int getCode() {return this.code;}
    public String getStatus() {return this.status; }
    public DataResponse getData() { return this.data; }
}
