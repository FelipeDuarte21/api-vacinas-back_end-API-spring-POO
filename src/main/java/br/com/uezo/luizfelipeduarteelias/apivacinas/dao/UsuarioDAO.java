package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);

}
