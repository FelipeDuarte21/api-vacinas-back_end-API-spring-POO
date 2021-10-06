package br.com.uezo.luizfelipeduarteelias.apivacinas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.uezo.luizfelipeduarteelias.apivacinas.dao.UsuarioDAO;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Endereco;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.HistoricoVacina;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.Usuario;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.HistoricoVacinaAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.ModeloAdapter;
import br.com.uezo.luizfelipeduarteelias.apivacinas.model.enums.TipoUsuario;

@Service
public class UsuarioService extends BaseService<Usuario, ModeloAdapter> {
	
	@Autowired
	private BaseService<Endereco, ModeloAdapter> enderecoService;
	
	@Autowired
	private BaseService<HistoricoVacina, HistoricoVacinaAdapter> historicoService;
	
	@Autowired
	private RestricaoService restricaoService;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Override
	public Usuario cadastrar(Usuario usuario) {
		
		Usuario u = this.usuarioDAO.findByEmail(usuario.getEmail());
		
		if(u != null) return null;
		
		Endereco endereco = this.enderecoService.cadastrar(usuario.getEndereco());
		
		usuario.setEndereco(endereco);
		
		usuario.setSenha(this.bCrypt.encode(usuario.getSenha()));
		
		usuario.getTipo().add(TipoUsuario.USUARIO.getCodigo());
		
		return super.repository.save(usuario);
		
	}

	@Override
	public Usuario atualizar(Usuario usuario) {
		
		if(usuario.getIdUsuario() == null) return null;
		
		Usuario u = this.buscarPorId(usuario.getIdUsuario());
		
		//Verifica se usuario é o mesmo que está logado
		this.restricaoService.verificarUsuario(usuario.getIdUsuario());
		
		Endereco endereco = this.enderecoService.atualizar(usuario.getEndereco());
		
		u.setEndereco(endereco);
		u.setNome(usuario.getNome());
		u.setEmail(usuario.getEmail());
		u.setSenha(u.getSenha());
		u.setDataNascimento(usuario.getDataNascimento()); 
		u.setSexo(usuario.getSexo());
		
		return super.repository.save(u);
		
	}

	@Override
	public boolean excluir(Long id) {
		
		Usuario usuario = this.buscarPorId(id);
		
		if(usuario == null) return false;
		
		//Verifica se usuario é o mesmo que está logado
		this.restricaoService.verificarUsuario(usuario.getIdUsuario());
		
		//Regra: Nenhuma
		
		usuario.getHistoricos().forEach(historico -> this.historicoService.excluir(historico));
		
		super.repository.delete(usuario);
		
		return true;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		
		Optional<Usuario> u = super.repository.findById(id);
		
		if(u.isEmpty()) return null;
		
		return u.get();
		
	}
	
	public Usuario buscarPorEmail(String email) {
		
		Usuario usuario = this.usuarioDAO.findByEmail(email);
		
		if(usuario == null) return null;
		
		this.restricaoService.verificarUsuario(usuario.getIdUsuario());
		
		return usuario;
		
	}

	
	//Métodos Não Utilizados
	@Override
	public Usuario cadastrarApdater(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public Usuario atualizarAdapter(ModeloAdapter modeloAdapter) {return null;}
	@Override
	public boolean excluir(Usuario modelo) {return false;}
	@Override
	public Page<Usuario> buscarTodos(Integer pagina, Integer quantidade) {return null;}

}