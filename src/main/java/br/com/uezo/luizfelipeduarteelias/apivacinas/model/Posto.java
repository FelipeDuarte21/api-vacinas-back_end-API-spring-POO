package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posto")
public class Posto extends Modelo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPosto;
	
	private String nome;
	private String telefone;
	private String diaHoraFuncionamento;
	
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "postos")
	private List<Campanha> campanhas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "posto")
	private List<HistoricoVacina> historicos = new ArrayList<>();
	
	public Posto() {
		
	}

	public Long getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(Long idPosto) {
		this.idPosto = idPosto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDiaHoraFuncionamento() {
		return diaHoraFuncionamento;
	}

	public void setDiaHoraFuncionamento(String diaHoraFuncionamento) {
		this.diaHoraFuncionamento = diaHoraFuncionamento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}

	public List<HistoricoVacina> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricoVacina> historicos) {
		this.historicos = historicos;
	}
	
}
