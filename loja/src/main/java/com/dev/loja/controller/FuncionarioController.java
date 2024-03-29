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

import com.dev.loja.models.Funcionario;
import com.dev.loja.repository.CidadeRepository;
import com.dev.loja.repository.FuncionarioRepository;
import com.dev.loja.repository.PapelRepository;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@GetMapping("/administrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCidades", cidadeRepository.findAll());
		mv.addObject("listaPapeis", papelRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return cadastrar(funcionario.get());
	}
	
	@GetMapping("/administrativo/funcionarios/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		funcionarioRepository.delete(funcionario.get());
		return listar();
	}
	
	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}
		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
		funcionarioRepository.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());
	}

}
