package com.nillo.libraryapi.repository;

import com.nillo.libraryapi.model.Autor;
import com.nillo.libraryapi.model.GeneroLivro;
import com.nillo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//CLASS DE TEST

//anotacao para subir a class de test
@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;//injetor o repository

    @Autowired
    LivroRepository livroRepository; //injetor o livro repository

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

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("teste");
        autor.setNacionalidade("Americana");
        autor.setData_nascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("20847-84874");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Medalha de Hora");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-84874");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("God of Wars");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }

    //erro no metodo
    @Test
    void listarlivrosAutor(){
        //obtendo o id
       var id = UUID.fromString("1d89c752-f137-46ea-ad88-30a9fc1a280d");
        var  autor = repository.findById(id).get();

        //buscar o livro peloa autor
      List<Livro> livrosLista =  livroRepository.findByAutor(autor);
      autor.setLivros(livrosLista);

      autor.getLivros().forEach(System.out::println);
   }
}
