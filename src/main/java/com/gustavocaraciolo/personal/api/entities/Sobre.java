package com.gustavocaraciolo.personal.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sobre")
public class Sobre extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "descricao", nullable = true)
	private String descricao;

	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
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
