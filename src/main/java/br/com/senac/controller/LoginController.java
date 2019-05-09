package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Usuario;
import br.com.senac.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/validar")
	public String login(Usuario usuario) {
		boolean decision = loginService.login(usuario);
		if (decision) {
			return "menu/menu.html";
		}
		return "erro/erro.html";
	}
	
	@GetMapping("/login")
	public ModelAndView validar() {
		ModelAndView mv = new ModelAndView("menu/login");
		mv.addObject("usuario", new Usuario());
		return mv;
	}	
}
