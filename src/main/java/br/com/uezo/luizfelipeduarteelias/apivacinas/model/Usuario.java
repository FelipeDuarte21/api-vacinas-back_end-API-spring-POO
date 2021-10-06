package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario extends Modelo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private String sexo;
	
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "usuario")
	private List<HistoricoVacina> historicos = new ArrayList<>();
	
	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipo_usuario")
	private Set<Integer> tipo = new HashSet<>();
	
	public Usuario() {
		super();
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<HistoricoVacina> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricoVacina> historicos) {
		this.historicos = historicos;
	}

	public Set<Integer> getTipo() {
		return tipo;
	}

	public void setTipo(Set<Integer> tipo) {
		this.tipo = tipo;
	}
	
}
