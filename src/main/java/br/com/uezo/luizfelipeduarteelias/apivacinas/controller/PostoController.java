package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Posto;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.PostoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/postos")
public class PostoController extends BaseController<Posto, ModeloAdapter> {
	
	@Autowired
	private PostoService postoService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Override
	public ResponseEntity<Posto> cadastrar(@RequestBody Posto posto) {
		
		try {

			posto = super.service.cadastrar(posto);
			
			if(posto == null) throw new ObjectBadRequestException("Erro! Posto já cadastrado!");
			
			URI url = new URI("/postos/" + posto.getIdPosto());
			return ResponseEntity.created(url).body(posto);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta!");
		}
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	@Override
	public ResponseEntity<Posto> atualizar(@RequestBody Posto posto) {
	
		posto = super.service.atualizar(posto);
		
		if(posto == null) throw new ObjectBadRequestException("Erro ao tentar atualizar posto!");
		
		return ResponseEntity.ok(posto);
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id) {
		
		boolean resp = super.service.excluir(id);
		
		if(resp == false) throw new ObjectBadRequestException("Erro! Não encontrado posto ou está associado!");
		
		return ResponseEntity.ok().build();

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Posto> buscarPorId(@PathVariable(name = "id") Long id) {
		
		Posto posto = super.service.buscarPorId(id);
		
		if(posto == null) throw new ObjectNotFoundException("Erro! Posto não econtrado!");
		
		return ResponseEntity.ok(posto);
		
	}

	@GetMapping
	@Override
	public ResponseEntity<Page<Posto>> buscarTodos(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade", defaultValue = "6") Integer quantidade) {
		
		Page<Posto> paginaPosto = super.service.buscarTodos(pagina, quantidade);
		
		return ResponseEntity.ok(paginaPosto);
		
	}
	
	@GetMapping("/localidade")
	public ResponseEntity<Page<Posto>> buscarPostoPorLocalidade(
			@RequestParam(name = "idCampanha") Long idCampanha,
			@RequestParam(name = "estado") String estado,
			@RequestParam(name = "cidade") String cidade,
			@RequestParam(name = "bairro")  String bairro,
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(name = "quantidade", defaultValue = "6") Integer quantidade){
		
		
		Page<Posto> paginaPosto = this.postoService.buscarPostoPorLocalidade(
				idCampanha, estado, cidade, bairro, pagina, quantidade);
		
		if(paginaPosto == null) throw new ObjectBadRequestException("Erro! Campanha não encontrada!");
		
		return ResponseEntity.ok(paginaPosto);
		
	}

	//Métodos não utilizados
	@Override
	public ResponseEntity<Posto> cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<Posto> atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<?> excluir(Posto modelo) {return null;}

}