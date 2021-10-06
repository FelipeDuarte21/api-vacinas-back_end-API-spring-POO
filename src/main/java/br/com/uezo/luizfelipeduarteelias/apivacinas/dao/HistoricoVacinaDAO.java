package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.HistoricoVacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;

@Repository
public interface HistoricoVacinaDAO extends JpaRepository<HistoricoVacina, Long> {
	
	public Page<HistoricoVacina> findByUsuario(Usuario usuario, Pageable page);
	
	public List<HistoricoVacina> findByVacina(Vacina vacina);
	
}
