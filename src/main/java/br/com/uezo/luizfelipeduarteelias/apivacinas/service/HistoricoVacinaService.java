package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.HistoricoVacinaDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.HistoricoVacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Posto;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.HistoricoVacinaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.VacinaAdapter;

@Service
public class HistoricoVacinaService extends BaseService<HistoricoVacina,HistoricoVacinaAdapter> {

	@Autowired
	private BaseService<Posto, ModeloAdapter> postoService;
	
	@Autowired
	private BaseService<Vacina, VacinaAdapter> vacinaService;
	
	@Autowired
	private HistoricoVacinaDAO historicoVacinaDAO;
	
	@Autowired
	private RestricaoService restricaoService;

	@Override
	public HistoricoVacina cadastrarApdater(HistoricoVacinaAdapter historicoVacinaAdapter) {
		
		Posto posto = this.postoService.buscarPorId(historicoVacinaAdapter.getIdPosto());
		
		if(posto == null) return null;
		
		Vacina vacina = this.vacinaService.buscarPorId(historicoVacinaAdapter.getIdVacina());
		
		if(vacina == null) return null;
				
		//Obtem o usuário Logado
		Usuario usuario = this.restricaoService.getUsuario();
				
		for(HistoricoVacina hv: usuario.getHistoricos()) {
			if(hv.getVacina().getIdVacina().equals(vacina.getIdVacina())) return null;
		}
		
		HistoricoVacina historico = HistoricoVacina
				.converteHistoricoVacinaAdapterParaHistoricoVacina(historicoVacinaAdapter);
		
		historico.setUsuario(usuario);
		historico.setPosto(posto);
		historico.setVacina(vacina);
		
		return super.repository.save(historico);
		
	}

	@Override
	public Page<HistoricoVacina> buscarTodos(Integer pagina, Integer quantidade) {
		
		PageRequest page = PageRequest.of(pagina, quantidade, Direction.ASC, "dataHora");
		
		//Obtem o usuário Logado
		Usuario usuario = this.restricaoService.getUsuario();
		
		Page<HistoricoVacina> pageHistorico = this.historicoVacinaDAO.findByUsuario(usuario, page);
		
		return pageHistorico;
		
	}
	
	public List<HistoricoVacina> buscarHistorico(Vacina vacina){
		
		List<HistoricoVacina> historico = this.historicoVacinaDAO.findByVacina(vacina);
		
		if(historico.isEmpty()) return null;
		
		return historico;
		
	}

	//Métodos não utilizados
	@Override
	public HistoricoVacina cadastrar(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public HistoricoVacina atualizar(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoricoVacina atualizarAdapter(HistoricoVacinaAdapter modeloAdapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HistoricoVacina buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
