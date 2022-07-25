package com.locacao.livros.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.locacao.livros.model.Aluguel;
import com.locacao.livros.repository.AluguelRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/aluguel")
public class AluguelController {
	@Autowired
	AluguelRepository aluguelRepository;
	@GetMapping()
	public ResponseEntity<List<Aluguel>> getAlugueis() {
		try {
			List<Aluguel> alugueis = new ArrayList<Aluguel>();
			alugueis = aluguelRepository.findAll();
			if (alugueis.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(alugueis, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Aluguel>> getAluguelByLivro(@PathVariable("id") long id) {
		try {
			List<Aluguel> alugueis = aluguelRepository.findByLivro(id);
			if (alugueis.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(alugueis, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Aluguel> createAluguel(@RequestBody Aluguel aluguel) {
		try {
			if (aluguel.getLivro().isAlugado())
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			Aluguel _aluguel = aluguelRepository.save(new Aluguel(aluguel.getDataInicio(), aluguel.getDataFim(), aluguel.getLivro()));
			return new ResponseEntity<>(_aluguel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluguel> updateAluguel(@PathVariable("id") long id) { //devolver
		try {
			Optional<Aluguel> aluguelData = Optional.of(aluguelRepository.findLastByLivro(id));
			if (aluguelData.isPresent()) {
				Aluguel _aluguel = aluguelData.get();
				_aluguel.setDataDevolucao(new Date());
				return new ResponseEntity<>(aluguelRepository.save(_aluguel), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
