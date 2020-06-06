package com.yelo.core.Entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="name", nullable=true)
	private String name;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="email", nullable=false)
	private String email;
	@Column(name="cellphone", nullable=false)
	private String cellphone;
	@Column(name="birthday", nullable=false)
	private Calendar birthday;
	@Column(name = "type", nullable = false)
	private String type;
	
	
	
	public Usuario() {
	}

	public Usuario(String name, String password, String email, String cellphone, Calendar birthday, String type) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.cellphone = cellphone;
		this.birthday = birthday;
		this.type = type;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public Calendar getBirthday() {
		return birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
