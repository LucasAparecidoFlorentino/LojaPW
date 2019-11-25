package com.dev.loja.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.models.Produto;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class CarrinhoController {

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho()  {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		return mv;
	}
	
	@GetMapping("/adicionarCarrinho/{id}")
	public ModelAndView adicionarCarrinho(@PathVariable Long id)  {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		return mv;
	}
}
