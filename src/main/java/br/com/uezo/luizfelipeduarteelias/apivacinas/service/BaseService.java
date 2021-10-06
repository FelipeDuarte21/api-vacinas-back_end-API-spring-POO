package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Modelo;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;

public abstract class BaseService<M extends Modelo, MA extends ModeloAdapter> {
	
	@Autowired
	protected JpaRepository<M,Long> repository;
	
	public abstract M cadastrar(M modelo);
	public abstract M cadastrarApdater(MA modeloAdapter);
	public abstract M atualizar(M modelo);
	public abstract M atualizarAdapter(MA modeloAdapter);
	public abstract boolean excluir(Long id);
	public abstract boolean excluir(M modelo);
	public abstract M buscarPorId(Long id);
	public abstract Page<M> buscarTodos(Integer pagina,Integer quantidade);
	
}
