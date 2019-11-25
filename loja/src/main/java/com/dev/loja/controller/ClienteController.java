package com.dev.loja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.models.Cidade;
import com.dev.loja.models.Cliente;
import com.dev.loja.repository.CidadeRepository;
import com.dev.loja.repository.ClienteRepository;
import com.dev.loja.repository.EstadoRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping("/cliente/cadastrar")
	public ModelAndView cadastrar(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastrar");
		mv.addObject("cliente", cliente);
		mv.addObject("listaCidades", cidadeRepository.findAll());
		return mv;
	}
	
//	@GetMapping("/administrativo/cidades/listar")
//	public ModelAndView listar() {
//		ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
//		mv.addObject("listaCidades", clienteRepository.findAll());
//		return mv;
//	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cadastrar(cliente.get());
	}
	
//	@GetMapping("/administrativo/cidades/remover/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Cidade> cidade = clienteRepository.findById(id);
//		clienteRepository.delete(cidade.get());
//		return listar();
//	}
	
	@PostMapping("/cliente/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(cliente);
		}
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		clienteRepository.saveAndFlush(cliente);
		return cadastrar(new Cliente());
	}

}
