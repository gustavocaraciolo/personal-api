package com.gustavocaraciolo.personal.api.entities;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

	/**
	 * Construtor
	 */
	protected AbstractEntity() {
	}

	/**
	 * Metodo responsável por getId
	 * 
	 * @return Number
	 * 
	 */
	public abstract Number getId();

}
