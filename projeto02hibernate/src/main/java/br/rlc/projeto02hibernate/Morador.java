package br.rlc.projeto02hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Morador")
public class Morador {
	
	private int id;
	private String apartamento;
	private String pin;
	
	public Morador() {
		
	}
	
	public Morador(String apartamento, String pin) {
		this.apartamento = apartamento;
		this.pin = pin;
	}
	
	@Id
	@Column(name = "ID_MORADOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
