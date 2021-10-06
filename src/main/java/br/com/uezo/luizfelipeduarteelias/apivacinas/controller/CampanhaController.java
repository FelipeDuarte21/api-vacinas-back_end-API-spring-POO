package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.ObjectBadRequestException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.ObjectNotFoundException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Campanha;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.CampanhaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.CampanhaService;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController extends BaseController<Campanha,CampanhaAdapter> {
	
	@Autowired
	private CampanhaService campanhaService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Override
	public ResponseEntity<Campanha> cadastrarApdater(@RequestBody CampanhaAdapter campanhaAdapter) {
		
		try {
			Campanha campanha = super.service.cadastrarApdater(campanhaAdapter);
			
			if(campanha == null) throw new ObjectBadRequestException("Erro! Erro no cadastro da campanha!");
			
			URI url = new URI("/campanhas/" + campanha.getIdCampanha());
			return ResponseEntity.created(url).body(campanha);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta!");
		}
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	@Override
	public ResponseEntity<Campanha> atualizarAdapter(@RequestBody CampanhaAdapter campanhaAdapter) {
		
		Campanha campanha = this.service.atualizarAdapter(campanhaAdapter);
		
		if(campanha == null) throw new ObjectBadRequestException("Erro! Erro ao atualizar campanha!");
		
		return ResponseEntity.ok(campanha);
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id) {
		
		boolean resp = super.service.excluir(id);
		
		if(resp == false) throw new ObjectBadRequestException("Erro! campanha não encontrada ou não pode ser excluída!");
		
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Campanha> buscarPorId(@PathVariable(name = "id") Long id) {
		
		Campanha campanha = super.service.buscarPorId(id);
		
		if(campanha == null) throw new ObjectNotFoundException("Erro! Campanha Não Encontrada!");
		
		return ResponseEntity.ok(campanha);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@Override
	public ResponseEntity<Page<Campanha>> buscarTodos(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade", defaultValue = "6") Integer quantidade) {
		
		Page<Campanha> paginaCampanha = super.service.buscarTodos(pagina, quantidade);
		
		return ResponseEntity.ok(paginaCampanha);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PatchMapping("/status")
	public ResponseEntity<Campanha> atualizarStatus(
			@RequestParam(name = "idCampanha") Long idCampanha, 
			@RequestParam(name = "status") Integer status){
		
		Campanha campanha = this.campanhaService.atualizarStatus(idCampanha,status);
		
		if(campanha == null) throw new ObjectBadRequestException(
				"Erro! Campanha não encontrada ou não pode ser atualizada para o status NOVA !");
		
		return ResponseEntity.ok(campanha);
	}
	
	@GetMapping("/disponiveis")
	public ResponseEntity<Page<Campanha>> buscarDisponiveis(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade", defaultValue = "6") Integer quantidade){
		
		Page<Campanha> campanhas = this.campanhaService.buscarDisponiveis(pagina, quantidade);
		
		return ResponseEntity.ok(campanhas);
		
	}


	//Métodos Não Utilizados
	@Override
	public ResponseEntity<Campanha> cadastrar(Campanha modelo) {return null;}
	@Override
	public ResponseEntity<Campanha> atualizar(Campanha modelo) {return null;}
	@Override
	public ResponseEntity<?> excluir(Campanha modelo) {return null;}

}
