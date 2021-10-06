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
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, ModeloAdapter> {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@Override
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		
		try {
			
			usuario = super.service.cadastrar(usuario);
			
			if(usuario == null) throw new ObjectBadRequestException("Erro! Usuário Já Cadastrado!");
			
			URI url = new URI("/usuarios/" + usuario.getIdUsuario());
			return ResponseEntity.created(url).body(usuario);
			
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao tentar devolver resposta!");
		}
		
	}

	@PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('USER')")
	@PutMapping
	@Override
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		usuario = super.service.atualizar(usuario);
		
		if(usuario == null) throw new ObjectBadRequestException("Erro! Usuário não econtrado!");
		
		return ResponseEntity.ok(usuario);
		
	}

	@PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('USER')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id) {
		
		boolean resp = super.service.excluir(id);
		
		if(resp == false) throw new ObjectBadRequestException("Erro! Usuário não encontrado!");
		
		return ResponseEntity.ok().build();
		
	}

	@PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('USER')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(name = "id") Long id) {
	
		Usuario usuario = super.service.buscarPorId(id);
		
		if(usuario == null) throw new ObjectNotFoundException("Erro! Usuário Não Encontado!");
		
		return ResponseEntity.ok(usuario);
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("/email")
	public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email){
		
		Usuario usuario = this.usuarioService.buscarPorEmail(email);
		
		if(usuario == null) {
			throw new ObjectNotFoundException("Usuário não encontrado para email informado!");
		}
		
		return ResponseEntity.ok(usuario);
	}

	//Métodos não utilizados
	@Override
	public ResponseEntity<Usuario> cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<Usuario> atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public ResponseEntity<?> excluir(Usuario modelo) {return null;}
	@Override
	public ResponseEntity<Page<Usuario>> buscarTodos(Integer pagina, Integer quantidade) {return null;}

}
