package com.gustavocaraciolo.personal.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavocaraciolo.personal.api.dtos.UsuarioDTO;
import com.gustavocaraciolo.personal.api.entities.Usuario;
import com.gustavocaraciolo.personal.api.response.Response;
import com.gustavocaraciolo.personal.api.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Response<UsuarioDTO>> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", usuarioDTO.toString());
		Response<UsuarioDTO> response = new Response<UsuarioDTO>();

		validarDadosExistentes(usuarioDTO, result);
		ModelMapper modelMapper = new ModelMapper();
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

		if (result.hasErrors()) {
			log.error("Erro validando dados do usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.persistir(usuario);
		response.setData(modelMapper.map(usuario, UsuarioDTO.class));
		return ResponseEntity.ok(response);
	}

	private void validarDadosExistentes(UsuarioDTO usuarioDTO, BindingResult result) {
		this.usuarioService.buscarPorEmail(usuarioDTO.getEmail())
				.ifPresent(usua -> result.addError(new ObjectError("usuario", "Usuário já existente.")));
	}

	@PostMapping(value = "/buscar/email")
	public ResponseEntity<Response<UsuarioDTO>> buscarPorCnpj(@RequestBody UsuarioDTO usuarioDTO) {
		String email = usuarioDTO.getEmail();
		log.info("Buscando usuário por email: {}", email);
		Response<UsuarioDTO> response = new Response<UsuarioDTO>();
		ModelMapper modelMapper = new ModelMapper();
		Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);

		if (!usuario.isPresent()) {
			log.info("Usuário não encontrado para o email: {}", email);
			response.getErrors().add("Usuário não encontrado para o email " + email);
			return ResponseEntity.badRequest().body(response);
		}

		UsuarioDTO retorno = modelMapper.map(usuario.get(), UsuarioDTO.class);
		response.setData(retorno);
		return ResponseEntity.ok(response);
	}

}
