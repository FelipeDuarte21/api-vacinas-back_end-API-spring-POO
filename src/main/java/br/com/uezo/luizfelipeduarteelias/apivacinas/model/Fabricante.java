package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fabricante")
public class Fabricante extends Modelo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFabricante;
	
	private String nome;
	private String cnpj;
	private String email;
	private String telefone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "fabricante")
	private List<Vacina> vacinas = new ArrayList<>();
	
	public Fabricante() {
		
	}
	
	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

}