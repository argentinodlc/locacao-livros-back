package com.locacao.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacao.livros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	List<Livro> findByAlugado(boolean alugado);
	List<Livro> findByTituloContaining(String titulo);
}
