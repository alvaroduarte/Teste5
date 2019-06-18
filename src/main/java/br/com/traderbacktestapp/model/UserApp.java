package br.com.traderbacktestapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserApp extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1499674398516773463L;

	@NotEmpty
	@Column(unique = true)
	private String username;
	
	//@JsonIgnore -> nao vai ficar navendado de uma lado para outro
	
	
	@JsonIgnore
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private boolean admin;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}