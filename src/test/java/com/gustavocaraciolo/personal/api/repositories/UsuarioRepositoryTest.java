package com.gustavocaraciolo.personal.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.gustavocaraciolo.personal.api.entities.Usuario;
import com.gustavocaraciolo.personal.api.enums.PerfilEnum;
import com.gustavocaraciolo.personal.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private static final Logger log = LoggerFactory.getLogger(UsuarioRepositoryTest.class);
	private static final String EMAIL = "teste@teste.com";

	@Before
	public void setUp() {
		try{
		Usuario usuario = new Usuario();
		usuario.setNome("Teste man");
		usuario.setEmail("teste@teste.com");
		usuario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
		this.usuarioRepository.save(usuario);
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

	@After
	public final void tearDown() {
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testBuscarPorEmail() {
		Usuario usuario = this.usuarioRepository.findByEmail(EMAIL);

		assertEquals(EMAIL, usuario.getEmail());
	}

}
