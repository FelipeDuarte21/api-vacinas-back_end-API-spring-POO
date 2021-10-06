package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.PostoDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Campanha;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Endereco;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Posto;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.CampanhaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;

@Service
public class PostoService extends BaseService<Posto, ModeloAdapter>{
	
	@Autowired
	private BaseService<Endereco, ModeloAdapter> enderecoService;
	
	@Autowired
	private BaseService<Campanha, CampanhaAdapter> campanhaService;
	
	@Autowired
	private PostoDAO postoDAO;

	@Override
	public Posto cadastrar(Posto posto) {
		
		Posto p = this.postoDAO.findByNome(posto.getNome());
		
		if(p != null) return null;
		
		Endereco endereco = this.enderecoService.cadastrar(posto.getEndereco());
		
		posto.setEndereco(endereco);
		
		return super.repository.save(posto);
		
	}

	@Override
	public Posto atualizar(Posto posto) {
		
		if(posto.getIdPosto() == null) return null;
		
		Posto p = this.buscarPorId(posto.getIdPosto());
		
		if(p == null) return null;
		
		Endereco endereco = this.enderecoService.atualizar(posto.getEndereco());
		
		if(endereco == null) return null;
		
		posto.setEndereco(endereco);
		
		return super.repository.save(posto);
		
	}
	
	@Override
	public boolean excluir(Long id) {
		
		Posto posto = this.buscarPorId(id);
		
		if(posto == null) return false;
		
		//Regra: Não pode excluir se tiver campanha e historico
		if(!posto.getCampanhas().isEmpty()) return false;
		if(!posto.getHistoricos().isEmpty()) return false;
		
		super.repository.delete(posto);
		
		this.enderecoService.excluir(posto.getEndereco());
		
		return true;
	}

	@Override
	public Posto buscarPorId(Long id) {
		
		Optional<Posto> p = super.repository.findById(id);
		
		if(p.isEmpty()) return null;
		
		return p.get();
	}

	@Override
	public Page<Posto> buscarTodos(Integer pagina, Integer quantidade) {
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "nome");
		
		Page<Posto> paginaPosto = super.repository.findAll(page);
		
		return paginaPosto;
		
	}
	
	public Page<Posto> buscarPostoPorLocalidade(Long idCampanha,String estado,
			String cidade,String bairro,Integer pagina,Integer quantidade){
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "nome");
		
		Campanha campanha = this.campanhaService.buscarPorId(idCampanha);
		
		if(campanha == null) return null;
		
		Page<Posto> paginaPosto = this.postoDAO.buscarPorLocalidade(estado, cidade, 
				bairro, campanha, page);

		
		return paginaPosto;
		
	}
	
	//Métodos não utilizados
	@Override
	public Posto cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public Posto atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public boolean excluir(Posto modelo) {return false;}

}