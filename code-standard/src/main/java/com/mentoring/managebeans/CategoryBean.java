package managebeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import daos.CategoryDao;
import entities.category;


public class CategoryBean implements actions {
	
	
	private List<category> categories;

	private CategoryDao categoryDao;	
	
	private int id;
	
	private category category = new category();


	@PostConstruct
	public void init() {

		 categories  = categoryDao.getCategories();

	}
	
	public List<category> getCategories() {


			return categories;
	} 
	
    
	public void setCategories(List<category> categories) {
		this.categories = categories;
	}

 

	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}


	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	

	
	public int getId() {
		return id;
	}


	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public category getCategory() {
		
		return category;
	}
	public void setCategory(category category) {
		
		this.category = category;
	} 
	
	 public void add(){
	    	
	    	categoryDao.add(category);
	    	
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The category was sucessfully added"));
	    	
	    	category = new category();
	    	
	    	categories  = categoryDao.getCategories();
	    	
	    }
	 
	 public void delete(int category){	
	    	
	    	categoryDao.delete(category);
	    	
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The category was sucessfully deleted"));
	    	
	    	categories  = categoryDao.getCategories();
	    	
	    	this.category = new category();
	    }
	 
	 public String update(){
		
		categoryDao.update(category);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The category was sucessfully updated"));
		
		
		categories = categoryDao.getCategories();
		
		category = new category();
		
		return "category";
	}
  
  
	public String select(int category){
		
		this.category = categoryDao.get(category);
		
		return "categoryUpdate";
		
	} 
	
	 public String view(int category){
		 
		 this.category = categoryDao.get(category);
		 
		 return "categoryQuestions";
		 
	 }
	 
	 
	 public String turnBack(){
		 
		 category = new category();
		 
		return "adminpage";
		 
	 }
	 public String getPage(){
			category = new category();
			return "category?faces-redirect=true";
		}
	
}
