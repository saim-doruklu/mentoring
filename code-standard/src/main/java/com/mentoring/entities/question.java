package com.mentoring.entities;



import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="question")
public class question  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private int id;
	
	private String name;
	
	private Date date=new Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name="category_id")
	
	private category category ;
	
	@ManyToOne 
	@JoinColumn(name="user_id")
	private user useri;
	
	@OneToOne(mappedBy="question",cascade=CascadeType.ALL)
	private Answer answer;
	
	@Column(nullable=true)
	private String image;
	
		
		public question() {
		super();
		
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
		
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		public category getCategori() {
			return category;
		}
		
		public void setCategory(category categori) {
			this.category = categori;
		}
		
		public user getUseri() {
			return useri;
		}
		
		public void setUseri(user useri) {
			this.useri = useri;
		}
		
		public Answer getAnswer() {
			return answer;
		}
		
		public void setAnswer(Answer answer) {
			this.answer = answer;
		}
		
		public category getCategory() {
			return category;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		
		

		

		

	
}

