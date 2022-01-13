package com.ZupAceleracaoSenior.RestLojaHQs.marvel.modelos;

public class ComicPrice {
    private String type;
    private float price;

    public ComicPrice() {
    }

    public void setType(String valor) { this.type = valor; }
    public void setPrice(float valor) { this.price = valor; }

    public String getType() { return this.type; }
    public float getPrice() { return this.price; }
}
