package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Usuario findByNome(String nome);
	
	Usuario findByEmail(String email);
	
	Usuario findByEmailAndSenha(String email, String senha);

}
