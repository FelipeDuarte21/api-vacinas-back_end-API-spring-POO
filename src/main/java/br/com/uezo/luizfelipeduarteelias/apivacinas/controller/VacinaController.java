package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.ObjectBadRequestException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.controller.exception.ObjectNotFoundException;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Vacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ComentarioAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.VacinaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.VacinaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vacinas")
public class VacinaController extends BaseController<Vacina, VacinaAdapter> {
	
	@Autowired
	private VacinaService vacinaService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Override
	public ResponseEntity<Vacina> cadastrarApdater(@RequestBody VacinaAdapter vacinaAdapter) {
		
		try {
			Vacina vacina = super.service.cadastrarApdater(vacinaAdapter);
			
			if(vacina == null) throw new ObjectBadRequestException("Erro! fabricante não encontrado!");
			
			URI url = new URI("/vacinas/" + vacina.getIdVacina());
			return ResponseEntity.created(url).body(vacina);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta");
		}
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	@Override
	public ResponseEntity<Vacina> atualizarAdapter(@RequestBody VacinaAdapter vacinaAdpater) {
		
		Vacina vacina = super.service.atualizarAdapter(vacinaAdpater);
		
		if(vacina == null) throw new ObjectBadRequestException("Erro! vacina não encontrada!");
		
		return ResponseEntity.ok(vacina);
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id) {
		
		boolean resp = super.service.excluir(id);
		
		if(resp == false) 
			throw new ObjectBadRequestException("Erro! Vacina não encontrada ou associada a campanha!");
		
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Vacina> buscarPorId(@PathVariable(name = "id") Long id) {
		
		Vacina vacina = super.service.buscarPorId(id);
		
		if(vacina == null) throw new ObjectNotFoundException("Erro! Vacina não encontrada!");
		
		return ResponseEntity.ok(vacina);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@Override
	public ResponseEntity<Page<Vacina>> buscarTodos(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade", defaultValue = "6")Integer quantidade) {
		
		Page<Vacina> paginaVacina = super.service.buscarTodos(pagina, quantidade);
		
		return ResponseEntity.ok(paginaVacina);
		
	}
	
	@GetMapping("/comentarios")
	public ResponseEntity<List<ComentarioAdapter>> buscarcomentarios(Long idVacina){
			
		List<ComentarioAdapter> comentarios = this.vacinaService.buscarComentarios(idVacina);
		
		if(comentarios == null) throw new ObjectBadRequestException("Erro! Sem comentários ou vacina não encontrada!");
		
		return ResponseEntity.ok(comentarios);
	}

	//Métodos Não Utilizados!
	@Override
	public ResponseEntity<Vacina> cadastrar(Vacina modelo) {return null;}
	@Override
	public ResponseEntity<Vacina> atualizar(Vacina modelo) {return null;}
	@Override
	public ResponseEntity<?> excluir(Vacina modelo) {return null;}

}