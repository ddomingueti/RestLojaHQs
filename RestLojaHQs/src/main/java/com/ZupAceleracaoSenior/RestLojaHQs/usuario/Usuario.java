package com.ZupAceleracaoSenior.RestLojaHQs.usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ZupAceleracaoSenior.RestLojaHQs.Validators.CpfConstraint;
import com.ZupAceleracaoSenior.RestLojaHQs.comic.Comic;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "O campo Nome é obrigatório.")
    @Size(max = 255, message = "O campo Nome não pode exceder 50 caracteres.")
    private String nome; //obrigatório
    
    @NotBlank(message = "O campo Email é obrigatório.")
    @Size(max = 255, message = "O campo Email não pode exceder 50 caracteres.")
    @Email
    @Column(unique = true)
    private String email; //usuario@host.com
    
    @NotBlank(message = "O campo CPF é obrigatório.")
    @Column(unique=true)
    @CpfConstraint
    private String cpf; //unico, 11 números
    
    @NotNull(message = "O campo Data de Nascimento é obrigatório, e deve ser informado no formado dd-mm-aaaa")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dataNascimento; //dd-mm-aaaa
    
    @ManyToMany
    @JoinTable(
        name="usuario_comic",
        joinColumns = {@JoinColumn(name="usuario_id")},
        inverseJoinColumns = {@JoinColumn(name="comic_id")}
    )
    private Set<Comic> listaHqs;
    
    public Usuario() {  }

    public Usuario(int id, String nome, String email, String cpf, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() { return this.nome; }
    public String getCpf() { return this.cpf; }
    public String getEmail() { return this.email; }
    
    public String getDataNascimento() { 
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dataFormat.format(this.dataNascimento);
    }

    public Set<Comic> getListaHqs() { return this.listaHqs; }

    public void setNome(String valor) { this.nome = valor; }
    public void setCpf(String valor) { this.cpf = valor; }
    public void setEmail(String valor) { this.email = valor; }
    public void setDataNascimento(Date valor) { this.dataNascimento = valor; }
    public void setListaHqs(Set<Comic> lista) { this.listaHqs = lista; }
}