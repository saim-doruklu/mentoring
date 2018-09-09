package daos;



import entities.category;

public class CategoryDao {
	
//-------------------------------------------------------------------------------------------------------------------------	
	public void add(category c){
		
	    Session session = hibernateUtil.getSessionFactory().openSession();
	    
	    try {
	        session.beginTransaction();
	        
	        session.save(c);
	        
	        session.getTransaction().commit();
	        
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
	    	
	        session.close();
	    }
	}

//-------------------------------------------------------------------------------------------------------------------------
	
	public void delete(int id){
		
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
           session.beginTransaction();
           
           category c = (category) session.load(category.class, new Integer(id));
           
            session.delete(c);
            
            session.getTransaction().commit();
            
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }
        finally{
        	
            session.close();
            
           }
	}
	
//------------------------------------------------------------------------------------------------------------------------
	
	public void update(category c){
		
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
            session.beginTransaction();
            
            session.update(c);
            
            session.getTransaction().commit();
            
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    } finally {
        	
             session.close();
        }
	}
	
//------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	
	public List<category> getCategories(){
		
    	List<category> categories = new ArrayList<category>();
    	
        Session session = hibernateUtil.getSessionFactory().openSession();
        
         try 
         {   session.beginTransaction();
         
             categories = session.createQuery("from category").getResultList();
             
         }catch(Exception e){
 	    	
 	    	e.printStackTrace();
 	    	
 	    }finally {
 	    	
             session.close();
         }
         return categories;
    	
    }
	
//------------------------------------------------------------------------------------------------------------------------

	public category get(int id){
		
	    category c = new category();
	    
        Session session = hibernateUtil.getSessionFactory().openSession();
        
        try {
        	
           session.beginTransaction();
           
           c = (category) session.load(category.class, new Integer(id));
           
           
        }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }  finally{
            
            session.close();
        }
        
        return c;
       
	}
}



