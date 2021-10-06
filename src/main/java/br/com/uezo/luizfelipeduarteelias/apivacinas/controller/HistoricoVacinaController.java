package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.ObjectBadRequestException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.HistoricoVacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.HistoricoVacinaAdapter;

@RestController
@RequestMapping("/historicos")
public class HistoricoVacinaController extends BaseController<HistoricoVacina,HistoricoVacinaAdapter> {

	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping
	@Override
	public ResponseEntity<HistoricoVacina> cadastrarApdater(
			@RequestBody HistoricoVacinaAdapter historicoVacinaAdapter) {
		
		try {
			HistoricoVacina historico = super.service.cadastrarApdater(historicoVacinaAdapter);
			
			if(historico == null) throw new ObjectBadRequestException("Erro! Erro ao tentar registrar historico!");
			
			URI url = new URI("/historicos/" + historico.getIdHistoricoVacina());
			return ResponseEntity.created(url).body(historico);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta!");
		}
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping
	@Override
	public ResponseEntity<Page<HistoricoVacina>> buscarTodos(
			@RequestParam(name = "pagina" ,defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade",defaultValue = "6")Integer quantidade) {
	
		Page<HistoricoVacina> historico = super.service.buscarTodos(pagina, quantidade);
		
		return ResponseEntity.ok(historico);
		
	}
	
	
	//Métodos não utilizados
	@Override
	public ResponseEntity<HistoricoVacina> cadastrar(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<HistoricoVacina> atualizar(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<HistoricoVacina> atualizarAdapter(HistoricoVacinaAdapter modeloAdapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> excluir(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> excluir(HistoricoVacina modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<HistoricoVacina> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
