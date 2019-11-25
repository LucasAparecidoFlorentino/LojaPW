package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.loja.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
