package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Fabricante;

@Repository
public interface FabricanteDAO extends JpaRepository<Fabricante,Long> {
	
	public Fabricante findByCnpj(String cnpj);
	
}
