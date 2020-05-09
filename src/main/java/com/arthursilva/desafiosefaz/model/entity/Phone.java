package com.arthursilva.desafiosefaz.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Phone {
	
	@NotNull(message = "DDD não pode ser vazio")
	@Column(nullable = false)
	private Short ddd;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 9, nullable = false)
	@NotBlank(message = "O Campo Telefone está vazio")
	@Size(min = 9, max = 9, message = "O Campo Telefone deve ter 9 caracteres")
	private String number;
	
	@Column(length = 20, nullable = false)
	private String type;
	
	@ManyToOne
	public User user;
	
	public short getDdd() {
		return ddd;
	}

	public void setDdd(short ddd) {
		this.ddd = ddd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
