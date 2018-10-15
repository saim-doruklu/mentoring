package com.mentoring.managebeans;

import com.mentoring.daos.SessionUtils;
import com.mentoring.daos.UserDao;
import com.mentoring.entities.user;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
   
@ManagedBean(name="loginBean")

@SessionScoped

public class LoginBean {
	
	
	private String username;
	
	private String password;
	
	private user user = new user();

	
	@ManagedProperty(value="#{userDao}")
	UserDao userDao;

	
	@PostConstruct
	public void init() {
	}

	public String login() {
	     FacesMessage message = null;
		
		user=userDao.getUser(password,username);
		
		if(user==null)
			
		{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
	}
		else{
			
			setData(user);
			if(user.getRoli().getName().equals("admin"))
			return "administrator/adminpage";
			else
				return "user/userpage";
		
		}
		
	}

	public UserDao getUserDao() {
		
		return userDao;
	}

	
	public void setUserDao(UserDao userDao) {
		
		this.userDao = userDao;
	}
	

	public user getUser() {
		
		return user;
	}

	
	public void setUser(user user) {
		
		this.user = user;
		
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

	
	public void setData(user user) {
		
		HttpSession session = SessionUtils.getSession();
		
		session.setAttribute("username", user.getUsername());
		
		session.setAttribute("id", user.getId());
		
	
	}
	public String logout() {
		
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/login?faces-redirect=true";
		
	}
	public String deactivateAccount(){
		if(user.getPassword().equals(password)){
			userDao.delete(user.getId());
			HttpSession session = SessionUtils.getSession();
			session.invalidate();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Your account was sucessfully deactivated!"));
			
			return "/login?faces-redirect=true";
	}
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please enter you password"));
		return null;
			
		
	}
	
}
