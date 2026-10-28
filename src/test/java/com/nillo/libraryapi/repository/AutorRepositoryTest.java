package com.nillo.libraryapi.repository;

import com.nillo.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//CLASS DE TEST

//anotacao para subir a class de test
@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;//injetor o repository

    //CRUD
    //executa os metodos salvar
    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasilerio");
        autor.setData_nascimento(LocalDate.of(1960, 1, 31));

        var autorSalvo = repository.save(autor);

        System.out.println("Autor Salvo: " + autorSalvo);

    }

    //executa os metodos atualizar
    @Test
    public void atualizar(){
      var id = UUID.fromString("a3553310-c0ee-47dd-a10e-ab6ec1fa89d5");

        Optional<Autor> possivelAutor = repository.findById(id);

      //verificando se o id exist ou
        if (possivelAutor.isPresent()){
            //obtendo o id encontrado e passando para uma variavel
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            //modificando os dados pelo id encontrado
            autorEncontrado.setData_nascimento(LocalDate.of(1990, 4, 12));

            //atualizando os dados
            repository.save(autorEncontrado);
        }else{
            System.out.println("Dados do Autor não encontrados:");
        }
    }

    //executa os metodos atualizar
    @Test
    public void listarTest(){
        //criando a lista de autrores
        List<Autor> lista = repository.findAll();

        //criando laco para percorrer toda a lista e capturando os dados
        lista.forEach(System.out::println);

    }

    //executa os metodo contador
    @Test
    public void countTest(){
        //chamando o metodo count()
        System.out.println("Contagem de autores: " + repository.count());
    }

    //executa os metodo deletar
    @Test
    public void deletarPorIdTest(){
        //obter o id
        var id = UUID.fromString("a3553310-c0ee-47dd-a10e-ab6ec1fa89d5");

        //chamando o metodo delete
        repository.deleteById(id);
    }

    //executa os metodo deletando pelo objeto
    @Test
    public void deletarObjTest(){
        //obter o id
        var id = UUID.fromString("1d2db15c-39d0-4f74-82a2-726db5c9f272");
        var teste = repository.findById(id).get();

        //chamando o metodo delete
        repository.delete(teste);
    }
}
