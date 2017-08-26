package com.iteractionapps.soccertalk.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	private ExternalContext externalContext;
	
	public String getNomeUsuario() {
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}
		
		return nome;
	}
        
        public String getEmailUsuario() {
		String email = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			email = usuarioLogado.getUsuario().getEmail();
		}
		
		return email;
	}
        
        public String getTimeUsuario() {
		String time = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			time = usuarioLogado.getUsuario().getClube().getNome();
		}
		
		return time;
	}
        
        public String getTimeSigla() {
		String time = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			time = usuarioLogado.getUsuario().getClube().getSigla();
		}
		
		return time;
	}
        
	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public boolean isEmitirPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") 
				|| externalContext.isUserInRole("VENDEDORES");
	}
	
	public boolean isCancelarPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES") 
				|| externalContext.isUserInRole("VENDEDORES");
	}
	
}
