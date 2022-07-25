package com.locacao.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locacao.livros.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
	@Query(value = "select * from Aluguel where id_livro = ?1 order by id desc limit 1", nativeQuery = true)
	Aluguel findLastByLivro(long id);
	@Query(value = "select * from Aluguel where id_livro = ?1 order by id desc", nativeQuery = true)
	List<Aluguel> findByLivro(long id);
}
