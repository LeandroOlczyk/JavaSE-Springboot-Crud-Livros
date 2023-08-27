package com.gestao.livros.repository;

import org.springframework.data.repository.CrudRepository;

import com.gestao.livros.model.Livro;

public interface LivrosRepository extends CrudRepository <Livro, String>{
		Livro findByCodigo(long codigo);
}
