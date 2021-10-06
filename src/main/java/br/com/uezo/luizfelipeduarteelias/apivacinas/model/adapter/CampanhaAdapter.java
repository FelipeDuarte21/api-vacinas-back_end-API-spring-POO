package br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampanhaAdapter extends ModeloAdapter {
	
	private Long id;
	private String titulo;
	private String subtitulo;
	private String publicoAlvo;
	private String faixaEtaria;
	private LocalDate dataComeco;
	private LocalDate dataTermino;
	private Long idVacina;
	private List<Long> idsPosto = new ArrayList<>();
	
	public CampanhaAdapter() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public List<Long> getIdsPosto() {
		return idsPosto;
	}

	public void setIdsPosto(List<Long> idsPosto) {
		this.idsPosto = idsPosto;
	}
	
}
