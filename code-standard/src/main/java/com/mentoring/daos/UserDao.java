package com.mentoring.daos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

import com.mentoring.entities.role;
import com.mentoring.entities.user;

@ManagedBean(name="userDao")

@ApplicationScoped
/*
 * Checkstyle checks :
 * Indentation should be white spaces instead of tabs
 * There should be brackets in if else statements
 *
 * PMD checks :
 * AvoidPrintStackTrace : Used logger instead
 * ShortVariable: Changed variable name add
 * MethodArgumentCouldBeFinal: Defined user as final in add
 * LocalVariableCouldBeFinal: Defined session as final in add
 * UselessParantheses: Removed parantheses in setRoli
 * UnnecessaryFullyQualifiedName: removed unnecessary package name in exist()
 * CommentDefaultAccessModifier: Commented logger
 * Only one return: Substituted two returns with one return in exist()
 * MethodNamingConventions: Method name Exist changed to exist
 * AtLeastOneConsructor: ? is it necessary
 * LinguisticNaming: name of the method setRoli changed to setRole
 *
 */
public class UserDao {
	/* default */ Logger logger = Logger.getLogger(UserDao.class.getName());
	
	public void add(final user user){
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
        	
        	session.beginTransaction();
        	
	        session.save(user);
	        
	        session.getTransaction().commit();
	        
	    }catch(Exception e){
	    	
	    	e.printStackTrace(); // AvoidPrintStackTrace : Use logger instead
          logger.log(Level.WARNING, "Hibernate error ",e);
	    	
	    } finally {
	         session.close();
	    }
	}
	
//------------------------------------------------------------------------------------------------------------------------
	
	public void delete(int id){
		
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            
            user u = session.load(user.class, new Integer(id));
            
            session.delete(u);
            
            session.getTransaction().commit();
            
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
        	
            session.close();
        }
        
	}
	
//------------------------------------------------------------------------------------------------------------------------	
        public void update(user u){
    	
            Session session = hibernateUtil.getSessionFactory().openSession();
            
            try {
                session.beginTransaction();
                
                session.update(u);
                
                session.getTransaction().commit();
                
            }catch(Exception e){
    	    	
    	    	e.printStackTrace();
    	    	
    	    }  finally {
                 session.close();
            }
    		
    	}
       
//------------------------------------------------------------------------------------------------------------------------        
        
		@SuppressWarnings("unchecked")
		
		public List<user> getUsers(){
			
        	List<user> users = new ArrayList<user>();
        	
             Session session = hibernateUtil.getSessionFactory().openSession();
             
             try{ 
            	session.beginTransaction();
            	
                users = session.createQuery("from user").getResultList();
                
             }catch(Exception e){
     	    	
     	    	e.printStackTrace();
     	    	
     	    } finally {
            	 
                 session.close();
             }
             return users;
        	
        }
		
//------------------------------------------------------------------------------------------------------------------------
		
		public user getUser(String password,String username){
			
	        Session session = hibernateUtil.getSessionFactory().openSession();
	        
	        try {  
	        	session.beginTransaction();
	        	
	            String sql =  "from user s where"
					+ " s.username='"+username+"' AND s.password='"+password+"'";
	            
	           return    (user) session.createQuery(sql).getSingleResult();
	           
	        }finally {
	        	
	             session.close();
	        }
	        
		}
		
//------------------------------------------------------------------------------------------------------------------------
	
		public role setRole(int id){
			
			 Session session = hibernateUtil.getSessionFactory().openSession();
			 
		        try {
		           session.beginTransaction();
		           
		           return session.load(role.class, new Integer(id));
		    
		        }finally {
		        	
		             session.close();
		        }
		      
		}
		
//------------------------------------------------------------------------------------------------------------------------
		public user get(int id){
			
	        Session session = hibernateUtil.getSessionFactory().openSession();
	        
	        user u = new user();
	        
	        try {
	        	
	            session.beginTransaction();
	            
	            u = session.load(user.class, new Integer(id));
	            
	            
	        }catch(Exception e){
		    	
		    	e.printStackTrace();
		    	
		    }finally {
	            session.close();
	        }
	        return u;
		}
//------------------------------------------------------------------------------------------------------------------------
		public boolean exist(String username){
Session session = hibernateUtil.getSessionFactory().openSession();
	        
	        try {  
	        	session.beginTransaction();
	        	
	            String sql =  "from user s where"
					+ " s.username='"+username+"'";
	            
	          user user =  (user) session.createQuery(sql).getSingleResult();
	          
	           return user != null;
	           
	        }finally {
	        	
	             session.close();
	        }
			
		}
		
}
