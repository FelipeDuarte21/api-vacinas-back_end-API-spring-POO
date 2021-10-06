package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Campanha;

@Repository
public interface CampanhaDAO extends JpaRepository<Campanha, Long> {
	
	public Page<Campanha> findByStatus(Integer status, Pageable page);
	
}
