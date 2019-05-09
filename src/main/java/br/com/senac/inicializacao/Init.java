package br.com.senac.inicializacao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Perfil;
import br.com.senac.dominio.Usuario;
import br.com.senac.repository.PerfilRepository;
import br.com.senac.repository.UsuarioRepository;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	@Autowired
	PerfilRepository perfilRepositorio;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Usuario usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setSobrenome("");
		usuario.setEmail("admin@senac.com");
		usuario.setSenha("1234");
		
		Perfil administrador = new Perfil();
		administrador.setNome("Administrador");
		
		Perfil tecnico = new Perfil();
		tecnico.setNome("Tecnico");
				
		usuario.setPerfis(Arrays.asList(administrador, tecnico));
		
		perfilRepositorio.saveAll(Arrays.asList(administrador, tecnico));
		usuarioRepositorio.save(usuario);
		
	}

}
