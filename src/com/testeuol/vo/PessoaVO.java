package com.testeuol.vo;

import java.io.Serializable;
import java.util.Date;

public class PessoaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nome;
	Date data;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
