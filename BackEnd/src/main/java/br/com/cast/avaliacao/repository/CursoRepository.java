package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>
, QuerydslPredicateExecutor<Curso>{

}