package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.loja.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
