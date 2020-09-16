package com.yelo.core.Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Usuario  implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private int id;
	
	@Column(name="name", nullable=false)
    @Basic(optional = false)
	private String name;
	
	@Column(name="password", nullable=false)
	@Basic(optional = false)
	private String password;
	
	@Column(name="email", nullable=false)
	@Basic(optional = false)
	private String email;
	
	@Column(name="cellphone", nullable=false)
	@Basic(optional = false)
	private String cellphone;
	
	@Column(name="birthday", nullable=false)
	@Temporal(TemporalType.DATE)
	@Basic(optional = false)
	private Calendar  birthday;
	
	@Basic(optional = false)
	@Column(name = "type", nullable = false)
	private String type;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Address> addresses; 
	
	
	
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
	
	public Usuario(int id) {
		super();
		this.id = id;
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
	
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", cellphone="
				+ cellphone + ", birthday=" + birthday + ", type=" + type + "]";
	}

	
	
	
	
	
	
}
