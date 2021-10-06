package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Fabricante;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController extends BaseController<Fabricante, ModeloAdapter>{

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@Override
	public ResponseEntity<Fabricante> cadastrar(@RequestBody Fabricante fabricante) {
		
		try {
			fabricante = super.service.cadastrar(fabricante);
			
			if(fabricante == null) throw new ObjectBadRequestException("Fabricante já cadastrado!");
			
			URI url = new URI("/fabricantes/" + fabricante.getIdFabricante());
			return ResponseEntity.created(url).body(fabricante);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta");
		}
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping
	@Override
	public ResponseEntity<Fabricante> atualizar(@RequestBody Fabricante fabricante) {
		
		fabricante = super.service.atualizar(fabricante);
		
		if(fabricante == null) throw new ObjectBadRequestException("Erro! verifique o id informado!");
		
		return ResponseEntity.ok(fabricante);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id) {
		
		boolean resp = super.service.excluir(id);
		
		if(resp == false) 
			throw new ObjectNotFoundException("Fabricante não encontrado ou existe vacinas cadastradas!");
		
		return ResponseEntity.ok().build();
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Fabricante> buscarPorId(@PathVariable(name = "id") Long id) {
		
		Fabricante fabricante = super.service.buscarPorId(id);
		
		if(fabricante == null) throw new ObjectNotFoundException("Erro! fabricante não encontrado!");
		
		return ResponseEntity.ok(fabricante);
		
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@Override
	public ResponseEntity<Page<Fabricante>> buscarTodos(
			@RequestParam(name = "pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(name = "quantidade", defaultValue = "6") Integer quantidade) {
		
		Page<Fabricante> paginaFabricante = super.service.buscarTodos(pagina, quantidade);
		
		return ResponseEntity.ok(paginaFabricante);
		
	}

	//Metodos não usados!
	@Override
	public ResponseEntity<Fabricante> cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<Fabricante> atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<?> excluir(Fabricante modelo) {return null;}
}
