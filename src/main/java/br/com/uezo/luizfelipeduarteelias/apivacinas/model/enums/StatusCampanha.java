package br.com.uezo.luizfelipeduarteelias.apivacinas.model.enums;

public enum StatusCampanha {
	
	NOVA(0,"Nova"),
	ATIVA(1,"Ativa"),
	SUSPENSA(2,"Suspensa"),
	DESATIVADA(3,"Desativada");
	
	private Integer id;
	private String descricao;
	
	private StatusCampanha(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}