package com.locacao.livros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "livro")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "sinopse")
	private String sinopse;
	@Column(name = "autor")
	private String autor;
	@Column(name = "alugado")
	private boolean alugado;
	public Livro() {
		
	}
	public Livro(String titulo, String sinopse, String autor, boolean alugado) {
		super();
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.autor = autor;
		this.alugado = false;
	}
	public Livro(long id, String titulo, String sinopse, String autor, boolean alugado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.autor = autor;
		this.alugado = alugado;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public boolean isAlugado() {
		return alugado;
	}
	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}
	
}
