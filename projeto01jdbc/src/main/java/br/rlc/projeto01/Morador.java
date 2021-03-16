package br.rlc.projeto01;

public class Morador {
	
	private String apartamento;
	private String pin;
	
	public Morador(String apartamento, String pin) {
		this.apartamento = apartamento;
		this.pin = pin;
	}
	
	public String getApartamento() {
		return apartamento;
	}
	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
}
