package br.com.senac.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Usuario;
import br.com.senac.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public Usuario buscar(Integer id) throws ObjectNotFoundException{
		Optional<Usuario> objUsuario = repoUsuario.findById(id);
		return objUsuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado! id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Usuario inserir(Usuario usuario) {
		usuario.setId(null);
		return repoUsuario.save(usuario);
	}
	
	public Usuario alterar(Usuario objUsuario) throws ObjectNotFoundException{
		Usuario objUsuarioEncontrado = buscar(objUsuario.getId());
		objUsuarioEncontrado.setNome(objUsuario.getNome());
		objUsuarioEncontrado.setSobrenome(objUsuario.getSobrenome());
		objUsuarioEncontrado.setEmail(objUsuario.getEmail());
		objUsuarioEncontrado.setSenha(objUsuario.getSenha());
		objUsuarioEncontrado.setPerfis(objUsuario.getPerfis());
		
		return repoUsuario.save(objUsuarioEncontrado);
	}
	
	public void excluir(Integer id) {
		repoUsuario.deleteById(id);
	}
	
	public List<Usuario> listaUsuario(){
		return repoUsuario.findAll();
	}

}
