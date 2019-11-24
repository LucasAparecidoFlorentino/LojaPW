package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.loja.models.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
