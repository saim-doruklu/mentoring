package entities;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class user  {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private role roli;
	
	@OneToMany(mappedBy = "useri", cascade = CascadeType.ALL)
	private List<question> questions;

	
	public user() {
	}
	

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	

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
	

	public role getRoli() {
		return roli;
	}
	

	public void setRoli(role roli) {
		this.roli = roli;
	}
	

	public List<question> getQuestions() {
		return questions;
	}

	
	public void setQuestions(List<question> questions) {
		this.questions = questions;
	}



	
}
