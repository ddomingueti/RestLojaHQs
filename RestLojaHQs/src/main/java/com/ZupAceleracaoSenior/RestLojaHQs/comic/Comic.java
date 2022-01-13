package com.ZupAceleracaoSenior.RestLojaHQs.comic;
import com.ZupAceleracaoSenior.RestLojaHQs.autor.Autor;
import com.ZupAceleracaoSenior.RestLojaHQs.preco.Preco;
import com.ZupAceleracaoSenior.RestLojaHQs.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comic")
public class Comic {
    
    @Id
    private int id;
    
    @NotBlank(message = "Campo obrigatório")
    private String titulo;
    
    @Lob
    @Column
    private String descricao;
    
    @OneToMany(mappedBy = "comic", cascade = CascadeType.ALL)
    private List<Preco> precos;

    @Column(unique = true)
    @NotBlank
    private String isbn;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name="comic_autor",
        joinColumns = {@JoinColumn(name="comic_id")},
        inverseJoinColumns = {@JoinColumn(name="autor_id")}
    )
    private Set<Autor> autoresHqs = new HashSet<Autor>();

    @JsonIgnore
    @ManyToMany(mappedBy = "listaHqs")
    private Set<Usuario> usuarios;
    
    private boolean temDesconto;

    public Comic() { }

    public Comic(int id, String titulo, String descricao, List<Preco> precos, String isbn, Set<Autor> autores) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.precos = precos;
        this.isbn = isbn;
        this.autoresHqs = autores;
        this.temDesconto = false;
    }

    public boolean verificaDesconto() {
        LocalDate data = LocalDate.now();
        DayOfWeek dia = data.getDayOfWeek();
        char digito = this.isbn.charAt(this.isbn.length() - 1);

        if (dia == DayOfWeek.MONDAY && (digito == '0' || digito == '1') ||
            dia == DayOfWeek.TUESDAY && (digito == '2' || digito == '3') ||
            dia == DayOfWeek.WEDNESDAY && (digito == '4' || digito == '5') ||
            dia == DayOfWeek.THURSDAY && (digito == '6' || digito == '7') ||
            dia == DayOfWeek.FRIDAY && (digito == '8' || digito == '9')) 
            temDesconto = true;
        
        return temDesconto;
    }

    public void aplicaDesconto() {
        for (Preco p : this.precos) {
            double novoPreco = p.getValor() - (p.getValor() * 0.1);
            p.setValor((float)novoPreco);
        }
    }
    
    public int getId() { return this.id; }
    public void setId(int valor) { this.id = valor; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String valor) { this.titulo = valor; }

    public String getDescricao() { return this.descricao; }
    public void setDescricao(String valor) { this.descricao = valor; }
    
    public  List<Preco> getPreco() { return this.precos; }
    public  void setPreco( List<Preco> arrayList) { this.precos = arrayList; }
    
    public String getIsbn() { return this.isbn; }
    public void setIsbn(String valor) { this.isbn = valor; }

    public Set<Autor> getAutores() { return this.autoresHqs; }
    public void setAutores(Set<Autor> valor) { this.autoresHqs = valor; } 

    public boolean getTemDesconto() { return this.temDesconto; }
    public void setTemDesconto(boolean desconto) { this.temDesconto = desconto; }

}

