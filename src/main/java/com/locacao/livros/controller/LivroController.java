package com.locacao.livros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.livros.model.Livro;
import com.locacao.livros.repository.LivroRepository;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/livro")
public class LivroController {
	@Autowired
	LivroRepository livroRepository;
	@GetMapping()
	public ResponseEntity<List<Livro>> getLivros(@RequestParam(required = false) String titulo) {
		try {
			List<Livro> livros = new ArrayList<Livro>();
			if (titulo == null)
				livros = livroRepository.findAll();
			else
				livros = livroRepository.findByTituloContaining(titulo);
			if (livros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(livros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable("id") long id) {
		try {
			Optional<Livro> livroData = livroRepository.findById(id);
			if (livroData.isPresent()) 
				return new ResponseEntity<>(livroData.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
		try {
			Livro _livro = livroRepository.save(new Livro(livro.getTitulo(), livro.getSinopse(), livro.getAutor(), false));
			return new ResponseEntity<>(_livro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable("id") long id, @RequestBody Livro livro) {
		try {
			Optional<Livro> livroData = livroRepository.findById(id);
			if (livroData.isPresent()) {
				Livro _livro = livroData.get();
				_livro.setTitulo(livro.getTitulo());
				_livro.setSinopse(livro.getSinopse());
				_livro.setAutor(livro.getAutor());
				_livro.setAlugado(livro.isAlugado());
				return new ResponseEntity<>(livroRepository.save(_livro), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deleteLivro(@PathVariable("id") long id) {
		try {
			livroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/alugado")
	public ResponseEntity<List<Livro>> findByAlugado() {
		try {
			List<Livro> livros = new ArrayList<Livro>();
			livros = livroRepository.findByAlugado(true);
			if (livros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(livros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
