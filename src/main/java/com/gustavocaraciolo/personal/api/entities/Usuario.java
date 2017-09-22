package com.gustavocaraciolo.personal.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.gustavocaraciolo.personal.api.enums.PerfilEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private PerfilEnum perfil;
	
	//Fetch Lazy -> Quando carregar um usuário, não carregar todas as habilidades
	//Fetch Eager -> Quando carregar um usuário, carregar todas as habilidades
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Habilidades> habilidades;
	
	@OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Sobre sobre;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
}


