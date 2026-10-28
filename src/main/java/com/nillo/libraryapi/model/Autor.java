package com.nillo.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//MAPEAMENTO DA TABELA AUTOR PARA O JPA

@Entity
@Table(name="autor", schema = "public")
@Getter
@Setter
@ToString (exclude = {"livros"})
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", length = 50, nullable = false)
    private LocalDate data_nascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros; //@Transient


    /*@Deprecated
    public Autor(){
        //para uso do framework
    }

    public Autor(String nome, String data_nascimento, String nacionalidade) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
    }*/
}
