package com.arthursilva.desafiosefaz.model.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@NotBlank(message = "O Campo Email está vazio")
	@Email(message = "informe um email valido!")
	@Size(min = 3, max = 60, message = "O Email deve ter de 3 a 60 caracteres!")
	@Column(unique = true, length = 60, nullable = false)
	private String email;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "O Campo Nome está vazio")
	@Size(min = 3, max = 60, message = "O Nome deve ter de 3 a 60 caracteres!")
	@Pattern(regexp = "[A-z áéíóúâêîôûãõ]+", message = "Nome Inválido")
	@OrderBy
	@Column(length = 60, nullable = false)
	private String name;
	
	@NotBlank(message = "O Campo Senha está vazio")
	@Size(min = 6, max = 50, message = "A Senha deve ter de 6 a 50 caracteres!")
	@Column(length = 100, nullable = false)
	private String password;
	
	@NotNull(message = "Você deve cadastrar pelo menos 1 telefone!")
	@Size(min = 1, message = "Você deve cadastrar pelo menos 1 telefone!")
	@OneToMany(
	        cascade = CascadeType.ALL,
	        fetch = FetchType.EAGER
	    )
	private Collection<Phone> phones;
	
	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}
	
	public Collection<Phone> getPhones() {
		return phones;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
