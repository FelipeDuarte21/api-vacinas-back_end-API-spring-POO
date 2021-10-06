package br.com.uezo.luizfelipeduarteelias.apivacinas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Endereco;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController extends BaseController<Endereco, ModeloAdapter> {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/estados")
	public ResponseEntity<List<String>> buscarEstados(){
		
		List<String> estados = this.enderecoService.buscarEstados();
		
		return ResponseEntity.ok(estados);
		
	}
	
	@GetMapping("/cidades")
	public ResponseEntity<List<String>> buscarCidades(
			@RequestParam(name = "estado") String estado){
		
		List<String> cidades = this.enderecoService.buscarCidades(estado);
		
		return ResponseEntity.ok(cidades);
	}
	
	@GetMapping("/bairros")
	public ResponseEntity<List<String>> buscarBairros(
			@RequestParam(name = "cidade") String  cidade){
		
		List<String> bairros = this.enderecoService.buscarBairros(cidade);
		
		return ResponseEntity.ok(bairros);
	}
	
	
	//Métodos não utilizados
	@Override
	public ResponseEntity<Endereco> cadastrar(Endereco modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Endereco> cadastrarApdater(ModeloAdapter modeloAdapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Endereco> atualizar(Endereco modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Endereco> atualizarAdapter(ModeloAdapter modeloAdapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> excluir(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> excluir(Endereco modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Endereco> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Page<Endereco>> buscarTodos(Integer pagina, Integer quantidade) {
		// TODO Auto-generated method stub
		return null;
	}


}
