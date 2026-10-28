package com.nillo.libraryapi.repository;

import com.nillo.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//INTERFACE REPOSITORY, COM INJEÇÃO DEPENDECIA JPAREPOSITORY COM PARAMETRO <Autor, UUID>
public interface AutorRepository extends JpaRepository<Autor, UUID> {



}
