package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Campanha;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Posto;

@Repository
public interface PostoDAO extends JpaRepository<Posto, Long> {
	
	public Posto findByNome(String nome);
	
	@Query("SELECT p FROM Posto p INNER JOIN p.campanhas c WHERE "
			+ "p.endereco.estado = ?1 AND p.endereco.cidade = ?2 AND p.endereco.bairro = ?3 "
			+ "AND c = ?4")
	public Page<Posto> buscarPorLocalidade(String estado,
			String cidade, String bairro, Campanha campanha, Pageable page);
	
}
