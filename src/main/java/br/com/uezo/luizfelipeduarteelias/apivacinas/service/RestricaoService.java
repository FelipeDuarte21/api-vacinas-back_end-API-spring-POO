package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.AuthorizationException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.UsuarioDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;

@Service
public class RestricaoService {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	
	private Usuario getUsuarioLogado() {
		try {
			String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario usuario = this.usuarioDao.findByEmail(email);
			return usuario;
			
		}catch(Exception e) {
			return null;
		}
	}
	
	public Usuario getUsuario() {
		
		Usuario usuario = this.getUsuarioLogado();
		
		if(usuario == null) throw new AuthorizationException("Usuário não logado");
		
		return usuario;
	}
	
	public void verificarUsuario(Long idComparado) {
		
		Usuario usuario = this.getUsuario();
		
		if(!usuario.getIdUsuario().equals(idComparado)) {
			throw new AuthorizationException("Acesso Indevido!");
		}
		
	}

}
