package com.ZupAceleracaoSenior.RestLojaHQs.autor;
import com.ZupAceleracaoSenior.RestLojaHQs.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank()
    private String nome;
    private String ocupacao;
    
    @ManyToMany(mappedBy = "autoresHqs", cascade = CascadeType.ALL)
    private Set<Comic> obras = new HashSet<Comic>();
    
    public Autor() { }

    public Autor(int id, String nome, Set<Comic> hqs) {
        this.nome = nome;
        this.id = id;
        this.obras = hqs;
    }

    public Autor(String nome, String ocupacao) {
        this.nome = nome;
        this.ocupacao = ocupacao;
    }

    public int getId() { return this.id; }
    public void setId(int valor) { this.id = valor; }
    
    @JsonIgnore
    public Set<Comic> getListaHQs() { return this.obras; }
    public void setListaHQs(Set<Comic> valor) { this.obras = valor; }

    public String getNome() { return this.nome; }
    public void setNome(String valor) { this.nome = valor; }

    public String getOcupacao() { return this.ocupacao; }
    public void setOcupacao(String valor) { this.ocupacao = valor; }
}
