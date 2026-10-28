package com.nillo.libraryapi.repository;

import com.nillo.libraryapi.model.Autor;
import com.nillo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//INTERFACE REPOSITORY, COM INJEÇÃO DEPENDECIA JPAREPOSITORY COM PARAMETRO <Livro, UUID>
public interface LivroRepository extends JpaRepository<Livro, UUID> {


    //metodo query
    //consulta select * from livro where id_autor = id
    List<Livro> findByAutor  (Autor autor);
    // select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    // select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);
}
