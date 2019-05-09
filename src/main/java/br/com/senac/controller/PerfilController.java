package br.com.senac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Perfil;
import br.com.senac.servico.PerfilService;

@Controller
@RequestMapping("/Perfil")
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping("/listar")
	public ModelAndView listaPerfis() {
		ModelAndView mv = new ModelAndView("perfil/listar");
		mv.addObject("perfis", perfilService.listaPerfis());
		return mv;
	}
	
	@GetMapping("/adicionar")
	public ModelAndView add(Perfil perfil) {
		ModelAndView mv = new ModelAndView("perfil/adicionar");
		mv.addObject("categorias", perfilService.listaPerfis());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView inserir(List<Perfil> perfil) {
		perfilService.inserir(perfil);
		return listaPerfis();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		perfilService.excluir(id);
		
		return listaPerfis();
	}
	
	@GetMapping("/altera/{id}")
	public ModelAndView alterar(@PathVariable("id")Integer id) {
		ModelAndView mv = new ModelAndView("perfil/alterar");
		mv.addObject("perfil", perfilService.buscar(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Perfil perfil) {
		perfilService.alterar(perfil);
		return listaPerfis();
	}
	
}
