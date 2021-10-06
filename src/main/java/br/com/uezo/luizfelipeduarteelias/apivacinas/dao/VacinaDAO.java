package br.com.uezo.luizfelipeduarteelias.apivacinas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;

@Repository
public interface VacinaDAO extends JpaRepository<Vacina, Long>  {

}