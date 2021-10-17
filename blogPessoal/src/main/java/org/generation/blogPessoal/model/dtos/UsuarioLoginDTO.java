package org.generation.blogPessoal.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioLoginDTO {
	private @NotBlank  String usuario; // Necessario Para Login
	private @NotBlank @Size(min = 5, max = 15, message = "Senha deve ter de 5 á 15 caracteres") String senha; // Necessario Para Login

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
