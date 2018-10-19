package com.mentoring.daos;

import com.mentoring.entities.role;
import com.mentoring.entities.user;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "userDao")

@ApplicationScoped
/*
 * Checkstyle checks :
 * Indentation should be white spaces instead of tabs
 * WhitespaceAround : in annotation around '=', before method opening braces, around '+'
 * TODO: figure out how to configure Checkstyle to detect unnecessary comment
 *
 * PMD checks :
 * AvoidPrintStackTrace : Used LOGGER instead
 * ShortVariable: Changed variable name add() from u to user
 * MethodArgumentCouldBeFinal: Defined user as final in add()
 * LocalVariableCouldBeFinal: Defined session as final in add()
 * UselessParantheses: Removed parantheses in setRoli
 * UnnecessaryFullyQualifiedName: removed unnecessary package name in exist()
 * CommentDefaultAccessModifier: Commented LOGGER as default
 * OnlyOneReturn: Substituted two returns with one return in exist()
 * MethodNamingConventions: Method name Exist changed to exist
 * AtLeastOneConsructor: is it necessary to have empty constructor?
 * LinguisticNaming: name of the method setRoli changed to getRole
 * LawOfDemeter: created static getSession() method in hibernateUtils to replace getSessionFactory().openSession()
 *               can't avoid in session.transaction.commit and session.createquery.getresultlist
 * AvoidCatchingGenericException: replaced generic Exception with HibernateException
 * CommentRequired: Are Public method and constructor comments required?
 * DataflowAnomalyAnalysis: How to handle two returns in try catch?
 * BeanMembersShouldSerialize: is it necessary?
 * IntegerInstantiation: removed newInteger() calls
 *
 */
public class UserDao {
	/* default */ private static final Logger LOGGER = Logger.getLogger(UserDao.class.getName());
	private static final String HIBERNATE_ERROR = "Hibernate error ";
	
	public void add(final user user) {
        Session session = HibernateUtil.getSession();
        
        try {
        	
        	session.beginTransaction();
        	
	        session.save(user);
	        
	        session.getTransaction().commit();
	        
	    }catch(HibernateException e){
	    	
	    	LOGGER.log(Level.WARNING, HIBERNATE_ERROR,e);
	    	
	    } finally {
	         session.close();
	    }
	}

	public void delete(int id){
		
        Session session = HibernateUtil.getSession();
        
        try {
            session.beginTransaction();
            
            user u = session.load(user.class, id);
            
            session.delete(u);
            
            session.getTransaction().commit();
            
        }catch(HibernateException e){

	    	LOGGER.log(Level.WARNING, HIBERNATE_ERROR,e);

	    } finally {
        	
            session.close();
        }
        
	}

	public void update(user u){
    	
            Session session = HibernateUtil.getSession();
            
            try {
                session.beginTransaction();
                
                session.update(u);
                
                session.getTransaction().commit();
                
            }catch(HibernateException e){
    	    	
    	    	LOGGER.log(Level.WARNING, HIBERNATE_ERROR,e);
    	    	
    	    }  finally {
                 session.close();
            }
    		
    	}

		@SuppressWarnings("unchecked")
		
		public List<user> getUsers(){
			
        	List<user> users = new ArrayList<user>();
        	
             Session session = HibernateUtil.getSession();
             
             try{ 
            	session.beginTransaction();
            	
                users = session.createQuery("from user").getResultList();
                
             }catch(HibernateException e){
     	    	
     	    	LOGGER.log(Level.WARNING, HIBERNATE_ERROR,e);
     	    	
     	    } finally {
            	 
                 session.close();
             }
             return users;

        }

		public user getUser(String password,String username){
			
	        Session session = HibernateUtil.getSession();
	        
	        try {  
	        	session.beginTransaction();
	        	
	            String sql =  "from user s where"
					+ " s.username='"+username+"' AND s.password='"+password+"'";
	            
	           return    (user) session.createQuery(sql).getSingleResult();
	           
	        }finally {
	        	
	             session.close();
	        }
	        
		}

		public role getRole(int id){
			
			 Session session = HibernateUtil.getSession();
			 
		        try {
		           session.beginTransaction();
		           
		           return session.load(role.class, id);
		    
		        }finally {
		        	
		             session.close();
		        }
		      
		}
		
		public user get(int id){
			
	        Session session = HibernateUtil.getSession();
	        
	        user u = new user();
	        
	        try {
	        	
	            session.beginTransaction();
	            
	            u = session.load(user.class, id);
	            
	            
	        }catch(HibernateException e){
		    	
		    	LOGGER.log(Level.WARNING, HIBERNATE_ERROR,e);
		    	
		    }finally {
	            session.close();
	        }
	        return u;
		}

		public boolean exist(String username){
          Session session = HibernateUtil.getSession();
	        
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
