package com.mentoring.daos;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.mentoring.entities.Answer;
import org.hibernate.Session;

@ManagedBean(name="answerDao")
@ApplicationScoped
public class AnswerDao {
//-------------------------------------------------------------------------------------------------------------------------	
public void add(Answer a){
		
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    try {
	        session.beginTransaction();
	        
	        session.save(a);
	        
	        session.getTransaction().commit();
	        
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }
	       
	     finally {
	    	 
	        session.close();
	    }
	    
	}
//--------------------------------------------------------------------------------------------------------------------------
	public void delete(int id){
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            session.beginTransaction();
            
            Answer a = (Answer) session.load(Answer.class, new Integer(id));
            
            session.delete(a);
            
            session.getTransaction().commit();
            
        
           
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
            
            session.close();
        }
	}
	
//-------------------------------------------------------------------------------------------------------------------------
	public void update(Answer q){
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
        	
             session.beginTransaction();
             
            session.update(q);
            
            session.getTransaction().commit();
     
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
           
            session.close();
        }
	}

}
