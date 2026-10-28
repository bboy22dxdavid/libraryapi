package com.nillo.libraryapi.repository;

import com.nillo.libraryapi.model.Autor;
import com.nillo.libraryapi.model.GeneroLivro;
import com.nillo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//CLASS DE TEST

//anotacao para subir a class de test
@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    AutorRepository autorRepository;  //injetor o repositoryAutor

    @Autowired
    LivroRepository repository; //injetor o repositoryLivro
    //executa os metodos salvar
    @Test
    void salvarTest(){
        //instanciando o um livro
        Livro livro = new Livro();

        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));



        //obtendo o id do autor
        Autor autor = autorRepository
                .findById(UUID.fromString("1bff67f5-76db-4738-a66f-9b1068c2fd7a"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    //SALVANDO COM O METODO CASCADE
    @Test
    void salvarCascedeTest(){
        //instanciando o um livro
        Livro livro = new Livro();

        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("novo livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        //criando autor com metodo de cascede
        Autor autor = new Autor();
        autor.setNome("joão");
        autor.setNacionalidade("Brasilerio");
        autor.setData_nascimento(LocalDate.of(1960, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    //METODO PARA ATUALIZAR
    @Test
    void atualizarTest(){
        UUID id = UUID.fromString("88d1e311-36ae-49ee-bc1d-7f4ba3451590");

        var  livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("a1dec0e8-3dc2-4006-906a-e6ce786b1cde");
        Autor joao = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(joao);

        repository.save(livroParaAtualizar);
    }

    //METODO PARA DELETAR PELO ID
    @Test
    void deletarTest(){
        //obtendo o id
        UUID id = UUID.fromString("88d1e311-36ae-49ee-bc1d-7f4ba3451590");

        //deletando o registro pelo Id
        repository.deleteById(id);
    }

    @Test
    void buscarLivroTest(){
        //obtendo o livro pelo Id
        UUID id = UUID.fromString("ff230d73-ef56-4c47-b81b-3a3eb30bd1a2");

        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livros");
        System.out.println(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }

    //METODO QUE REALIZA A PESQUIZA POR TITULO
    @Test
    void pesquisarPorTituloTest(){
        //obtendo a lista de titulos para realizar a consulta
        List<Livro> lista = repository.findByTitulo("God of Wars");

        //printando a tela
        lista.forEach(System.out::println);
    }

    //METODO QUE REALIZA A PESQUIZA POR TITULO
    @Test
    void pesquisarPorIsbnTest(){
        //obtendo a lista de titulos para realizar a consulta
        List<Livro> lista = repository.findByIsbn("90887-84874");

        //printando a tela
        lista.forEach(System.out::println);
    }

    //METODO QUE REALIZA A PESQUIZA POR TITULO E PRECO
    @Test
    void pesquisarPorPrecoTest(){
        //VARIAVEL
        var preco =  BigDecimal.valueOf(100.00);
        var tituloPesquisado = "UFO";

        //obtendo a lista de titulos para realizar a consulta
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisado, preco);

        //printando a tela
        lista.forEach(System.out::println);
    }

    //METODO QUE REALIZA A PESQUIZA POR TITULO OU PRECO
    @Test
    void pesquisarPorPrecoOuPrecoTest(){
        //VARIAVEL
        var isbn =  "90887-84874";
        var tituloPesquisado = "UFO";

        //obtendo a lista de titulos para realizar a consulta
        List<Livro> lista = repository.findByTituloOrIsbn(tituloPesquisado, isbn);

        //printando a tela
        lista.forEach(System.out::println);
    }


}
