package br.com.uezo.luizfelipeduarteelias.apivacinas.model.adapter;

public class VacinaAdapter extends ModeloAdapter{
	
	private Long id;
	private String nome;
	private Integer dose;
	private Long idFabricante;
	
	public VacinaAdapter() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}
	
}