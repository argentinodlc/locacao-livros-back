package com.locacao.livros.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "aluguel")
public class Aluguel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "dataInicio")
	private Date dataInicio;
	@Column(name="dataFim")
	private Date dataFim;
	@Column(name="dataDevolucao")
	private Date dataDevolucao;
	@ManyToOne
	@JoinColumn(name="idLivro")
	private Livro livro;
	public Aluguel() {
		
	}
	public Aluguel(Date dataInicio, Date dataFim, Livro livro) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.livro = livro;
	}
	
	public Aluguel(long id, Date dataInicio, Date dataFim, Date dataDevolucao, Livro livro) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataDevolucao = dataDevolucao;
		this.livro = livro;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}
