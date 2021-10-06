package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.CampanhaDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Campanha;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Posto;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.CampanhaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.VacinaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.enums.StatusCampanha;

@Service
public class CampanhaService extends BaseService<Campanha,CampanhaAdapter> {
	
	@Autowired
	private BaseService<Vacina, VacinaAdapter> vacinaService;
	
	@Autowired
	private BaseService<Posto,ModeloAdapter> postoService;
	
	@Autowired
	private CampanhaDAO campanhaDao;

	@Override
	public Campanha cadastrarApdater(CampanhaAdapter campanhaAdapter) {
		
		Vacina vacina = this.vacinaService.buscarPorId(campanhaAdapter.getIdVacina());
		
		if(vacina == null) return null;
		
		List<Posto> postos = new ArrayList<>();
		
		campanhaAdapter.getIdsPosto().forEach(id -> {
			Posto p = this.postoService.buscarPorId(id);
			if(p != null) postos.add(p);
		});
		
		if(postos.isEmpty()) return null;
		
		Campanha campanha = Campanha.converteCampanhaAdapterParaCampanha(campanhaAdapter);
		campanha.setStatus(StatusCampanha.NOVA.getId());
		campanha.setVacina(vacina);
		campanha.setPostos(postos);
		
		return super.repository.save(campanha);
	}

	@Override
	public Campanha atualizarAdapter(CampanhaAdapter campanhaAdapter) {
		
		Campanha c = this.buscarPorId(campanhaAdapter.getId());
		
		if(c == null) return null;
		
		Vacina vacina = this.vacinaService.buscarPorId(campanhaAdapter.getIdVacina());
		
		if(vacina == null) return null;
		
		List<Posto> postos = new ArrayList<>();
		campanhaAdapter.getIdsPosto().forEach(id -> {
			Posto p = this.postoService.buscarPorId(id);
			if(p != null) postos.add(p);
		});
		
		if(postos.isEmpty()) return null;
		
		Campanha campanha = Campanha.converteCampanhaAdapterParaCampanha(campanhaAdapter);
		
		campanha.setVacina(vacina);
		campanha.setPostos(postos);
		campanha.setStatus(c.getStatus());
		
		return super.repository.save(campanha);
		
	}

	@Override
	public boolean excluir(Long id) {
		
		Campanha campanha = this.buscarPorId(id);
		
		if(campanha == null) return false;
		
		//Regras: Só pode excluir campanhas com status Desativada ou Nova
		if(!campanha.getStatus().equals(StatusCampanha.DESATIVADA.getId()) && 
				!campanha.getStatus().equals(StatusCampanha.NOVA.getId())) return false;
		
		super.repository.delete(campanha);
		return true;
	}

	@Override
	public Campanha buscarPorId(Long id) {
		
		Optional<Campanha> c = super.repository.findById(id);
		
		if(c.isEmpty()) return null;
		 
		return c.get();
	}

	@Override
	public Page<Campanha> buscarTodos(Integer pagina, Integer quantidade) {
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "titulo");
		
		Page<Campanha> paginaCampanha = super.repository.findAll(page);
		
		return paginaCampanha;
	}
	
	public Campanha atualizarStatus(Long idCampanha, Integer status) {
		
		Campanha campanha = this.buscarPorId(idCampanha);
		
		if(campanha == null) return null;
		
		//Regra: Não pode definir com o status Nova
		if(status.equals(StatusCampanha.NOVA.getId())) return null;
		
		campanha.setStatus(status);
		
		return super.repository.save(campanha);
		
	}
	
	public Page<Campanha> buscarDisponiveis(Integer pagina, Integer quantidade){
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "dataComeco");
		
		Page<Campanha> campanhas = this.campanhaDao.findByStatus(StatusCampanha.ATIVA.getId(), page);
		
		return campanhas;
		
	}
	
	@Override
	public Campanha cadastrar(Campanha modelo) {return null;}
	@Override
	public Campanha atualizar(Campanha modelo) {return null;}
	@Override
	public boolean excluir(Campanha modelo) {return false;}

}
