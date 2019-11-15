package com.dev.loja.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.models.EntradaItens;
import com.dev.loja.models.EntradaProduto;
import com.dev.loja.models.Estado;
import com.dev.loja.models.Estado;
import com.dev.loja.repository.EntradaItensRepository;
import com.dev.loja.repository.EntradaProdutoRepository;
import com.dev.loja.repository.EstadoRepository;
import com.dev.loja.repository.FuncionarioRepository;

@Controller
public class EntradaProdutoController {
	
	@Autowired
	private EntradaProdutoRepository entradaProdutoRepository;
	
	@Autowired
	private EntradaItensRepository entradaItensRepository;
	
	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView cadastrar(EntradaProduto entrada, List<EntradaItens> listaEntradaItens, EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", listaEntradaItens);
		mv.addObject("entradaItens", entradaItens);
		return mv;
	}
	
	/*@GetMapping("/administrativo/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaEstados", estadoRepository.findAll());
		return mv;
	}*/
	
	/*@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		return cadastrar(estado.get());
	}*/
	
	/*@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		estadoRepository.delete(estado.get());
		return listar();
	}*/
	
	@PostMapping("/administrativo/entrada/salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, List<EntradaItens> listaEntrada, EntradaItens entradaItens) {
		
		if(acao.equals("itens")) {
			listaEntrada.add(entradaItens);
		}
		
		return cadastrar(entrada, listaEntrada, new EntradaItens());
	}

}
