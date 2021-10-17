package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;
    private Usuario usuarioUpdate;
    private Usuario usuarioAdmin;

    @BeforeEach
    public void start() {

        usuarioAdmin = new Usuario("Jose Langue", "joseteste", "jose.langue@email.com", "123654987");

        usuarioUpdate = new Usuario(2L, "Fagner Antunes", "fag.ner", "fagner.a@email.com", "123654987");

    }

    @Test
    @DisplayName("Cadastrar Usuário!")
    @Order(1)
    public void deveRealizarPostUsuario() {

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
        ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

    }

    @Disabled
    @Test
    @DisplayName("Listar todos os  Usuário!")
    @Order(2)
    public void deveRealizarGetAllUsuario() {

        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin@mail.com", "123654987").exchange("/usuarios/all", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }

    @Disabled
    @Test
    @DisplayName("Alterar Usuário!")
    @Order(3)
    public void deveRealizarPutUsuario() {

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
        ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("admin@mail.com", "123654987").exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

    }

}