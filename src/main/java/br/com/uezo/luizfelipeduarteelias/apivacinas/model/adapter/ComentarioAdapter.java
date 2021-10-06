package br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter;

import java.time.LocalDateTime;

public class ComentarioAdapter extends ModeloAdapter {
	
	private String usuario;
	private LocalDateTime dataHora;
	private String comentario;
	
	public ComentarioAdapter() {
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
}