package br.com.uezo.luizfelipeduarteelias.apivacinas.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.enums.TipoUsuario;

public class UsuarioDetalhe implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	
	private Collection<? extends GrantedAuthority> autorizacoes;
	
	public UsuarioDetalhe(Long id, String email,String senha,Set<Integer> tipos) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.autorizacoes = tipos.stream().map(
				t -> new SimpleGrantedAuthority(TipoUsuario.toEnum(t).getDescricao()) ).collect(Collectors.toList());
				
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autorizacoes;
	}
	
	public Long getId() {
		return this.id;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public boolean hasRole(String role) {
		return this.autorizacoes.contains(new SimpleGrantedAuthority(role));
	}

}
