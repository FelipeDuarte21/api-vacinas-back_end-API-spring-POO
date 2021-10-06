package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.VacinaAdapter;

@Entity
@Table(name = "vacina")
public class Vacina extends Modelo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacina;
	
	private String nome;
	private Integer dose;
	
	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vacina")
	private List<Campanha> campanhas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "vacina")
	private List<HistoricoVacina> historicos = new ArrayList<>();
	
	public Vacina() {
		
	}

	public Long getIdVacina() {
		return idVacina;
	}

	public void setId(Long idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDose() {
		return dose;
	}

	public void setDose(Integer dose) {
		this.dose = dose;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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
	
	public static Vacina converteVacinaAdapterParaVacina(VacinaAdapter vacinaAdapter) {
		Vacina vacina = new Vacina();
		vacina.setId(vacinaAdapter.getId()); 
		vacina.setNome(vacinaAdapter.getNome());
		vacina.setDose(vacinaAdapter.getDose());
		return vacina;
	}

}
