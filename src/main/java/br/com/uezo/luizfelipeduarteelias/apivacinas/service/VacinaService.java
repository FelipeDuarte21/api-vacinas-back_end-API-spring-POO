package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Fabricante;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.HistoricoVacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ComentarioAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.VacinaAdapter;

@Service
public class VacinaService extends BaseService<Vacina, VacinaAdapter> {
	
	@Autowired
	private BaseService<Fabricante, ModeloAdapter> fabricanteService;
	
	@Autowired
	private HistoricoVacinaService historicoVacinaService;

	@Override
	public Vacina cadastrarApdater(VacinaAdapter vacinaAdapter) {
		
		Fabricante fabricante = this.fabricanteService.buscarPorId(vacinaAdapter.getIdFabricante());
		
		if(fabricante == null) return null;
		
		Vacina vacina = Vacina.converteVacinaAdapterParaVacina(vacinaAdapter);
		
		vacina.setFabricante(fabricante);
		
		return super.repository.save(vacina);
		
	}

	@Override
	public Vacina atualizarAdapter(VacinaAdapter vacinaAdapter) {
		
		if(vacinaAdapter.getId() == null) return null;
		
		Vacina vacina = this.buscarPorId(vacinaAdapter.getId());
		
		if(vacina == null) return null;
		
		vacina.setNome(vacinaAdapter.getNome());
		vacina.setDose(vacinaAdapter.getDose());
		
		return super.repository.save(vacina);
	}

	@Override
	public boolean excluir(Long id) {
		
		Vacina vacina = this.buscarPorId(id);
		
		if(vacina == null) return false;
		
		//Regra: Não pode excluir com campanhas e historicos associados
		if(!vacina.getCampanhas().isEmpty()) return false;
		if(!vacina.getHistoricos().isEmpty()) return false;
		
		super.repository.delete(vacina);
		
		return true;
	}

	@Override
	public Vacina buscarPorId(Long id) {
		
		Optional<Vacina> v = super.repository.findById(id);
		
		if(v.isEmpty()) return null;
		
		return v.get();
	}

	@Override
	public Page<Vacina> buscarTodos(Integer pagina, Integer quantidade) {
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "nome");
		
		return super.repository.findAll(page);
		
	}
	
	public List<ComentarioAdapter> buscarComentarios(Long idVacina){
		
		Vacina vacina = this.buscarPorId(idVacina);
		
		if(vacina == null) return null;
		
		List<HistoricoVacina> historico = this.historicoVacinaService.buscarHistorico(vacina);
		
		if(historico == null) return null;
		
		List<ComentarioAdapter> comentarios = new ArrayList<>();
		
		for(HistoricoVacina hv: historico) {
			ComentarioAdapter ca = new ComentarioAdapter();
			ca.setUsuario(hv.getUsuario().getNome());
			ca.setDataHora(hv.getDataHora());
			ca.setComentario(hv.getComentario());
			comentarios.add(ca);
		}
		
		return comentarios;
		
	}

	@Override
	public Vacina cadastrar(Vacina modelo) {return null;}
	@Override
	public Vacina atualizar(Vacina modelo) {return null;}
	@Override
	public boolean excluir(Vacina modelo) {return false;}
	
}