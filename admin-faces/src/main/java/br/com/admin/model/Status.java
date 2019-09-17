package br.com.admin.model;

public enum Status {
	
	ANDAMENTO("Em Andamento"),
	FINALIZADO("Finalizado");
	
	private String descricao;

	private Status(String descricao) {
		this.descricao= descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
