package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.loja.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
