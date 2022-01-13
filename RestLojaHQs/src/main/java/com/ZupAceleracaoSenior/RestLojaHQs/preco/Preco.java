package com.ZupAceleracaoSenior.RestLojaHQs.preco;
import com.ZupAceleracaoSenior.RestLojaHQs.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comic_preco")
public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String tipo;
    @NotNull
    private float valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comic_id", referencedColumnName = "id")
    private Comic comic;
    
    public Preco(String tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Preco() { }

    public int getId() { return this.id; }
    public void setId(int valor) { this.id = valor; }

    public String getTipo() { return this.tipo; }
    
    public float getValor() { return this.valor; }
    public void setValor(float valor) { this.valor = valor; }
    
    @JsonIgnore
    public Comic getComic() { return this.comic; }
    public void setComic(Comic comic) { this.comic = comic; }
}
