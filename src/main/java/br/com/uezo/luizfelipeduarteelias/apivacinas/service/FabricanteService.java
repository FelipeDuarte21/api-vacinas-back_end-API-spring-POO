package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.FabricanteDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Fabricante;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;

@Service
public class FabricanteService extends BaseService<Fabricante, ModeloAdapter> {
	
	@Autowired
	private FabricanteDAO fabricanteDAO;

	@Override
	public Fabricante cadastrar(Fabricante fabricante) {
		
		Fabricante f = this.fabricanteDAO.findByCnpj(fabricante.getCnpj());
		
		if(f != null) return null;
		
		return super.repository.save(fabricante);
		
	}

	@Override
	public Fabricante atualizar(Fabricante fabricante) {
		
		if(fabricante.getIdFabricante() == null) return null;
		
		Fabricante f = this.buscarPorId(fabricante.getIdFabricante());
		
		if(f == null) return null;
	
		return super.repository.save(fabricante);
		
	}


	@Override
	public boolean excluir(Long id) {
		
		Fabricante fabricante = this.buscarPorId(id);
		
		if(fabricante == null) return false;
		
		//Regra: Não pode excluir caso tenha vacina cadastrada
		if(!fabricante.getVacinas().isEmpty()) return false;
		
		super.repository.delete(fabricante);
		
		return true;
		
	}

	@Override
	public Fabricante buscarPorId(Long id) {
		
		Optional<Fabricante> f = super.repository.findById(id);
		
		if(f.isEmpty()) return null;
		
		return f.get();
		
	}

	@Override
	public Page<Fabricante> buscarTodos(Integer pagina, Integer quantidade) {
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "nome");
		
		return super.repository.findAll(page);
		
	}
	
	//Métodos não utilizados
	@Override
	public Fabricante cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public Fabricante atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public boolean excluir(Fabricante modelo) {return false;}

}