package com.mentoring.entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role")
public class role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="roli",cascade=CascadeType.ALL)
	private List<user> user;
	
	
	public role() {
	
	}
	
	public role(String name) {
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<user> getUser() {
		return user;
	}
	
	public void setUser(List<user> user) {
		this.user = user;
	}
	
	
}
