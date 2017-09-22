package com.gustavocaraciolo.personal.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavocaraciolo.personal.api.entities.Usuario;
import com.gustavocaraciolo.personal.api.repositories.UsuarioRepository;
import com.gustavocaraciolo.personal.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando um usuario com email {}", email);
		return Optional.ofNullable(usuarioRepository.findByEmail(email));
	}

	@Override
	public Usuario persistir(Usuario usuario) {
		log.info("Buscando usuario {}", usuario);
		return this.usuarioRepository.save(usuario);
	}
}
