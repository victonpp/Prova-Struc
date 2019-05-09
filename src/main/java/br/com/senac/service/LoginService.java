package br.com.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Usuario;
import br.com.senac.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository repoUsuario;

	public boolean login(Usuario usuario) {
		Usuario usuarioLogado = repoUsuario.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		if (usuarioLogado == null) {
			return false;
		}
		return true;
	}
	
}
