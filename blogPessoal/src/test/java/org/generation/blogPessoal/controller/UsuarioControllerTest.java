package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.Usuario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class UsuarioControladorTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private Usuario usuario;
	private Usuario usuarioAlterar;

	@BeforeAll
	public void start() {
		usuario = new Usuario("RafaelBoaz", "134652");
		usuarioAlterar = new Usuario("RafaelBoaz", "654321");
	}

	@Test
	@Order(1)
	@DisplayName("✔ Cadastrar Usuário!")
	void deveSalvarUsuarioRetornaStatus201() {

		/*
		 * Criando um objeto do tipo HttpEntity para enviar como terceiro parâmentro do
		 * método exchange. (Enviando um objeto usuario via body)
		 * 
		 */
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("usuario/salvar", HttpMethod.POST, request,
				Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("👍 Listar todos os Usuários!")
	void deveRetornarListadeUsuarioRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("RafaelBoaz", "134652")
				.exchange("usuario/todos", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("😳 Alterar Usuário!")
	void deveAtualizarSenhaUsuarioRetornaStatus201() {

		/*
		 * Criando um objeto do tipo HttpEntity para enviar como terceiro parâmentro do
		 * método exchange. (Enviando um objeto usuario via body)
		 * 
		 */
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAlterar);

		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("RafaelBoaz", "134652")
				.exchange("usuario/alterar", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

}