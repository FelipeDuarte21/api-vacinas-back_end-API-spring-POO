package br.com.uezo.luizfelipeduarteelias.apivacinas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.UsuarioDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = this.usuarioDao.findByEmail(email);
		
		if(usuario == null) throw new UsernameNotFoundException(email);
		
		return new UsuarioDetalhe(usuario.getIdUsuario(),usuario.getEmail(),
				usuario.getSenha(),usuario.getTipo());
		
	}

}