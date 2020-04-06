package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
, QuerydslPredicateExecutor<Categoria> {

}