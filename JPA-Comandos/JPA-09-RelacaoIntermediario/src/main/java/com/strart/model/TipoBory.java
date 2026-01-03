package com.strart.model;

public enum TipoBory {
	START(6), MIDDLE(9), END(9), CERTIFICADO(3);

	private final int limite;

	TipoBory(int limite) {
		this.limite = limite;
	}

	public int getLimite() {
		return limite;
	}
}
