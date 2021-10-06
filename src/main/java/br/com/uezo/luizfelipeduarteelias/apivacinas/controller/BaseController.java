package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Modelo;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.BaseService;

public abstract class BaseController<M extends Modelo, MA extends ModeloAdapter> {
	
	@Autowired
	protected BaseService<M,MA> service;
	
	public abstract ResponseEntity<M> cadastrar(M modelo);
	public abstract ResponseEntity<M> cadastrarApdater(MA modeloAdapter);
	public abstract ResponseEntity<M> atualizar(M modelo);
	public abstract ResponseEntity<M> atualizarAdapter(MA modeloAdapter);
	public abstract ResponseEntity<?> excluir(Long id);
	public abstract ResponseEntity<?> excluir(M modelo);
	public abstract ResponseEntity<M> buscarPorId(Long id);
	public abstract ResponseEntity<Page<M>> buscarTodos(Integer pagina,Integer quantidade);

}
