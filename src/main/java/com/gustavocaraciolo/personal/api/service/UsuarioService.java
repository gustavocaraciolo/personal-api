package com.gustavocaraciolo.personal.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gustavocaraciolo.personal.api.entities.Usuario;

@Service
public interface UsuarioService {

	Optional<Usuario> buscarPorEmail(String email);

	Usuario persistir(Usuario usuario);
}
