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
import br.com.senac.dominio.Usuario;
import br.com.senac.service.ObjectNotFoundException;
import br.com.senac.service.PerfilService;
import br.com.senac.service.UsuarioService;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping("/listar")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("usuario/listar");
		mv.addObject("usuarios", usuarioService.listaUsuario());
		return mv;
	}
	
	@GetMapping("/adicionar")
	public ModelAndView adicionar() {
		ModelAndView mv = new ModelAndView("usuario/adicionar");
		mv.addObject("usuario", new Usuario());
		mv.addObject("perfis", perfilService.listaPerfis());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView inserir(Usuario usuario) {
		usuarioService.inserir(usuario);
		perfilService.inserir(usuario.getPerfis());
		return listaUsuarios();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		usuarioService.excluir(id);
		return listaUsuarios();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException{
		ModelAndView mv = new ModelAndView("usuario/alterar");
		mv.addObject("usuario", usuarioService.buscar(id));
		mv.addObject("perfis", perfilService.listaPerfis());
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Usuario usuario) throws ObjectNotFoundException{
		usuarioService.alterar(usuario);
		return listaUsuarios();
	}
}

