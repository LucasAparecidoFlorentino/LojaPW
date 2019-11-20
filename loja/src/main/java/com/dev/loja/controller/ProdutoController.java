package com.dev.loja.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.models.Produto;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private static String caminhoImagens = "C:/Users/Aluno/Downloads/LojaPW/imagens/";

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/administrativo/produtos/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("produto", produto);
		return mv;
	}

	@GetMapping("/administrativo/produtos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("listaProdutos", produtoRepository.findAll());
		return mv;
	}

	@GetMapping("/administrativo/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return cadastrar(produto.get());
	}

	@GetMapping("/administrativo/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		produtoRepository.delete(produto.get());
		return listar();
	}

	@PostMapping("/administrativo/produtos/salvar")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result,
			@RequestParam("file") MultipartFile arquivo) {

		if (result.hasErrors()) {
			return cadastrar(produto);
		}

		produtoRepository.saveAndFlush(produto);

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				produto.setNomeImagem(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cadastrar(new Produto());
	}

}
