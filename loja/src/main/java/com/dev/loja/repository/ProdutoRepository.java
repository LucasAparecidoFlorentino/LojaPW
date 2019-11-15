package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.loja.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
