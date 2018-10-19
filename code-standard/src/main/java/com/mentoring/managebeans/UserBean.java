package com.mentoring.managebeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mentoring.daos.UserDao;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements actions {
	
	private com.mentoring.entities.user user = new com.mentoring.entities.user();
	
	@ManagedProperty(value = "#{userDao}")
	private UserDao userDao;
	
	private List<com.mentoring.entities.user> users;
	
	private String password;
	
	private String newPassword;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	

	@PostConstruct
	public void init() {
	}

	public com.mentoring.entities.user getUser() {
		return user;
	}

	public void setUser(com.mentoring.entities.user user) {
		this.user = user;
	}

	public List<com.mentoring.entities.user> getUsers() {
		
		users = userDao.getUsers();
		
		return users;
	}

	public void setUsers(List<com.mentoring.entities.user> users) {
		
		this.users = users;
	}

	public UserDao getUserDao() {
		
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		
		this.userDao = userDao;
	}

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public void changePassword(){
		
		if((loginBean.getUser().getPassword().equals(this.password)))
			{	
			if((newPassword.length()<=5)||(newPassword.equals("password")||(newPassword.equals(loginBean.getUser().getUsername())))){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "The password you set is not allowed or too weak, please try another! "));
				
			}
			else{
			loginBean.getUser().setPassword(newPassword);
			    
			userDao.update(loginBean.getUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The password was sucessfully changed! "));
			newPassword = null;
			
			}
			}
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The Old password you enter doesnt match the password"));
	
	}
	
	public void add(){
		if(userDao.exist(user.getUsername())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn!", "This username exist . Try another."));
		}
		else{
			if((user.getPassword().length()<=5)||(user.getPassword().equals("password"))){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "The password you set is not allowed or too weak, please try another! "));
				
			}
			
			else{
		user.setRoli(userDao.getRole(2));
		userDao.add(user);
		user = new com.mentoring.entities.user();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO! ", "Your account was sucessfully created"));
		}
		}
	}

	public void delete(int user) {
		
		users.remove(userDao.get(user));
		
		userDao.delete(user); 
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The account was sucessfully deleted"));
		
		
		users = userDao.getUsers();
	}
	
	public String update(){
		if((user.getPassword().length()<=5)||(user.getPassword().equals("password"))){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "The password you set is not allowed or too weak, please try another! "));
			return null;
		}
		else{
		
		userDao.update(user);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The account was sucessfully updated"));
		
		user = new com.mentoring.entities.user();
		
	return "user";	}
	}
	
	public String select (int user){
		
		this.user = userDao.get(user);
		
		return "userUpdate";
	}
	
	public String view(int user){
		
		this.user = userDao.get(user);  
		
		return "userQuestions";
		
	}
	
	public String turnBack(){
		
		user = new com.mentoring.entities.user();
		
		return "adminpage";
	}
	public String getPage(){
		user = new com.mentoring.entities.user();
		return "user?faces-redirect=true";
	}
	
    
}
