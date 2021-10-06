package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Long> {
	
	public Endereco findByCepAndNumero(String cep,String numero);
	
	@Query("SELECT DISTINCT e.estado FROM Endereco e")
	public List<String> findDistinctByEstado();
	
	@Query("SELECT DISTINCT e.cidade FROM Endereco e WHERE e.estado = ?1")
	public List<String> findDistinctByCidade(String estado);
	
	@Query("SELECT DISTINCT e.bairro FROM Endereco e WHERE e.cidade = ?1")
	public List<String> findDistinctByBairro(String cidade);
	
}
