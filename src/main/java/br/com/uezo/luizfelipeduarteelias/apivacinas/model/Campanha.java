package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.CampanhaAdapter;

@Entity
@Table(name = "campanha")
public class Campanha extends Modelo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCampanha;
	
	private String titulo;
	private String subtitulo;
	private String publicoAlvo;
	private String faixaEtaria;
	private LocalDate dataComeco;
	private LocalDate dataTermino;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "id_vacina")
	private Vacina vacina;
	
	@ManyToMany
	@JoinTable(name = "campanha_posto",
		joinColumns = {@JoinColumn(name = "id_campanha")},
		inverseJoinColumns = {@JoinColumn(name = "id_posto")})
	private List<Posto> postos = new ArrayList<>();
	
	public Campanha() {
		
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public LocalDate getDataComeco() {
		return dataComeco;
	}

	public void setDataComeco(LocalDate dataComeco) {
		this.dataComeco = dataComeco;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public List<Posto> getPostos() {
		return postos;
	}

	public void setPostos(List<Posto> postos) {
		this.postos = postos;
	}
	
	public static Campanha converteCampanhaAdapterParaCampanha(CampanhaAdapter ca) {
		Campanha c = new Campanha();
		c.setIdCampanha(ca.getId());
		c.setTitulo(ca.getTitulo());
		c.setSubtitulo(ca.getSubtitulo());
		c.setPublicoAlvo(ca.getPublicoAlvo());
		c.setFaixaEtaria(ca.getFaixaEtaria());
		c.setDataComeco(ca.getDataComeco());
		c.setDataTermino(ca.getDataTermino());
		return c;
	}
	
}
