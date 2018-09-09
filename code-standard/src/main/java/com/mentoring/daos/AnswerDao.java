package daos;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import entities.answer;

@ManagedBean(name="answerDao")
@ApplicationScoped
public class AnswerDao {
//-------------------------------------------------------------------------------------------------------------------------	
public void add(answer a){
		
	    Session session = hibernateUtil.getSessionFactory().openSession();
	    
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
		
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
        	
            session.beginTransaction();
            
            answer a = (answer) session.load(answer.class, new Integer(id));
            
            session.delete(a);
            
            session.getTransaction().commit();
            
        
           
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
            
            session.close();
        }
	}
	
//-------------------------------------------------------------------------------------------------------------------------
	public void update(answer q){
		
        Session session = hibernateUtil.getSessionFactory().openSession();
        
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
