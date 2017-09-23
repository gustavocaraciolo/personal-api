package com.gustavocaraciolo.personal.api.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Email deve conter entre 3 e 200 caracteres.")
	@Email(message = "Email inválido.")
	private String email;
	private String senha;
}
