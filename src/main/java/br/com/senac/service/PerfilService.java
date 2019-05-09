package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Perfil;
import br.com.senac.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repoPerfil;

	public Perfil buscar(Integer id) {
		Optional<Perfil> objPerfil = repoPerfil.findById(id);
		return objPerfil.orElseThrow(() -> new ObjectNotFoundException(
				"Perfil não encontrado! Id: " + id + ", Tipo: " + Perfil.class.getName()));
	}

	public Perfil inserir(List<Perfil> perfis) {
		for (int i = 0; i<perfis.size();i++) {
			Perfil perfil = new Perfil();
			perfil.setId(null);
			perfil.setNome(perfis.get(i).getNome());
			return repoPerfil.save(perfil);
		}
		return null;
	}
	
	public Perfil alterar(Perfil objPerfil) {
		Perfil objPerfilEncontrado = buscar(objPerfil.getId());
		objPerfilEncontrado.setNome(objPerfil.getNome());
		return repoPerfil.save(objPerfilEncontrado);
	}
	
	public void excluir(Integer id) {
		repoPerfil.deleteById(id);
	}

	public List<Perfil> listaPerfis() {
		return repoPerfil.findAll();
	}

}
