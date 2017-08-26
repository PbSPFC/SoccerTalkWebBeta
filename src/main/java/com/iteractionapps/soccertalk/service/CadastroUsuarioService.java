package com.iteractionapps.soccertalk.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.iteractionapps.soccertalk.model.Usuario;
import com.iteractionapps.soccertalk.repository.Clubes;
import com.iteractionapps.soccertalk.repository.Usuarios;
import com.iteractionapps.soccertalk.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
        @Inject
        private Clubes clubes;
        
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
                
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuario com o Email informado.");
		}
		
		return usuarios.guardar(usuario);
	}
	
}
