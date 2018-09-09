package daos;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

import entities.role;
import entities.user;

@ManagedBean(name="userDao")

@ApplicationScoped

public class UserDao {
	
	
	public void add(user u){
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
        	
        	session.beginTransaction();
        	
	        session.save(u);
	        
	        session.getTransaction().commit();
	        
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
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
			
        	List<user> users = new ArrayList<>();
        	
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
	
		public role setRoli(int id){
			
			 Session session = hibernateUtil.getSessionFactory().openSession();
			 
		        try {
		           session.beginTransaction();
		           
		           return( session.load(role.class, new Integer(id)));
		    
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
		public boolean Exist(String username){
Session session = hibernateUtil.getSessionFactory().openSession();
	        
	        try {  
	        	session.beginTransaction();
	        	
	            String sql =  "from user s where"
					+ " s.username='"+username+"'";
	            
	          user user =  (entities.user) session.createQuery(sql).getSingleResult();
	          
	           if(user==null)
	        	   
	        	   return false;
	           
	           else   
	        	   
	        	   return true;
	           
	        }finally {
	        	
	             session.close();
	        }
			
		}
		
}
