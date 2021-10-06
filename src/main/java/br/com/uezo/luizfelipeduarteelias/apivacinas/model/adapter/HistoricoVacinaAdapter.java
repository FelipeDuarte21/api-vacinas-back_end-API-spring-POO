package br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter;

import java.time.LocalDateTime;

public class HistoricoVacinaAdapter extends ModeloAdapter {
	
	private Long id;
	private LocalDateTime dataHora;
	private String comentario;
	private Long idVacina;
	private Long idPosto;
	
	public HistoricoVacinaAdapter() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public Long getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(Long idPosto) {
		this.idPosto = idPosto;
	}
	
}
