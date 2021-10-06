package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.EnderecoDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Endereco;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;

@Service
public class EnderecoService extends BaseService<Endereco, ModeloAdapter> {
	
	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public Endereco cadastrar(Endereco endereco) {
		
		Endereco e = this.enderecoDAO.findByCepAndNumero(endereco.getCep(), endereco.getNumero());
		
		if(e != null) return e;
		
		return super.repository.save(endereco);
		
	}

	@Override
	public Endereco atualizar(Endereco endereco) {
		
		if(endereco.getIdEndereco() == null) return null;
		
		Endereco e = this.buscarPorId(endereco.getIdEndereco());
		
		if(e == null) return null;
		
		return super.repository.save(endereco);
		
	}

	@Override
	public boolean excluir(Endereco endereco) {
		
		super.repository.delete(endereco);
		
		return true;
		
	}

	@Override
	public Endereco buscarPorId(Long id) {
		
		Optional<Endereco> e = super.repository.findById(id);
		
		if(e.isEmpty()) return null;
		
		return e.get();
	}
	
	public List<String> buscarEstados(){
		
		return this.enderecoDAO.findDistinctByEstado();
		
	}
	
	public List<String> buscarCidades(String estado){
		
		return this.enderecoDAO.findDistinctByCidade(estado);
		
	}
	
	public List<String> buscarBairros(String cidade){
		
		return this.enderecoDAO.findDistinctByBairro(cidade);
		
	}

	//Métodos não utilizados
	@Override
	public Page<Endereco> buscarTodos(Integer pagina, Integer quantidade) {return null;}
	@Override
	public Endereco cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public Endereco atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public boolean excluir(Long id) {return false;}

}
