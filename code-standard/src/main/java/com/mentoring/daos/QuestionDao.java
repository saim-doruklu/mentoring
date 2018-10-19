package com.mentoring.daos;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.mentoring.entities.category;
import org.hibernate.Session;

import com.mentoring.entities.question;

@ManagedBean(name = "questionDao")

@ApplicationScoped
/*
* PMD checks:
* ForLoopCanBeForEach: replaced for loop with foreach
* ControlStatementBraces: added braces around if else
* VariableNamingConventions: replaced method argument name id_categ with idCategory
* AvoidLiteralsInIfCondition: extracted field from integer in if control statement
 */
public class QuestionDao {

	private final int maxCategories = 20;

	public void add(question q) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			
			session.beginTransaction();
			
			session.save(q);
			
			session.getTransaction().commit();

		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
	    	
			session.close();
		}
	}

	public void delete(int id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {

			session.beginTransaction();
			
			question q = (question) session.load(question.class, new Integer(id));
			
			session.delete(q);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }  finally {
	    	
			session.close();
		}
	}

	public void update(question q) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			session.update(q);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }  finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	
	public List<question> getQuestion() {
		
		List<question> quests = new ArrayList<question>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			quests = session.createQuery("from question").getResultList();
		
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }finally {
			session.close();
		}
		return quests;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<question> getQuestionUser(int id) {
		
		List<question> quests = new ArrayList<question>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			
			session.beginTransaction();
			
			quests = session.createQuery("from question where user_id="+id).getResultList();
		
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }finally {
			session.close();
		}
		return quests;
	}
	

	@SuppressWarnings("unchecked")
	
	public List<question> getQuestionRandom(int idCategory, int m) {

		List<question> quest = new ArrayList<question>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			
			quest = session.createQuery("from question where category_id=" + idCategory + " order by rand() ").setMaxResults(m).getResultList();
			
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
		}finally {
			session.close();
		}
		return quest;

	}

	public List<question> createQuiz() {
	
		int m;
		
		CategoryDao categoryDao = new CategoryDao();
		
		List<com.mentoring.entities.category> categories = categoryDao.getCategories();
		
		List<question> questions = new ArrayList<question>();

		if(categories.size()> maxCategories) {

			m = 1;

		} else {
			
		m = maxCategories / categories.size();
		}

		for (com.mentoring.entities.category category : categories) {

			questions.addAll(getQuestionRandom(category.getId(), m));

		}
		return questions;
	}

	public question get(int id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		question q = new question();
		
		try {

			session.beginTransaction();
			
			q = (question) session.load(question.class, new Integer(id));
				
		}catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }  finally {
	    	
			session.close();
		           }
		
		return q;
	}

}
