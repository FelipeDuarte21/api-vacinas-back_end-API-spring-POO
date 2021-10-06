package br.com.uezo.luizfelipeduarteelias.apivacinas.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter.HistoricoVacinaAdapter;

@Entity
@Table(name = "historico_vacina")
public class HistoricoVacina extends Modelo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHistoricoVacina;
	
	private LocalDateTime dataHora;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name = "id_posto")
	private Posto posto;
	
	@ManyToOne
	@JoinColumn(name = "id_vacina")
	private Vacina vacina;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public HistoricoVacina() {
		
	}

	public Long getIdHistoricoVacina() {
		return idHistoricoVacina;
	}

	public void setIdHistoricoVacina(Long idHistoricoVacina) {
		this.idHistoricoVacina = idHistoricoVacina;
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

	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public static HistoricoVacina converteHistoricoVacinaAdapterParaHistoricoVacina(
			HistoricoVacinaAdapter hva) {
		HistoricoVacina hc = new HistoricoVacina();
		hc.setIdHistoricoVacina(hva.getId());
		hc.setDataHora(hva.getDataHora());
		hc.setComentario(hva.getComentario());
		return hc;
	}

}