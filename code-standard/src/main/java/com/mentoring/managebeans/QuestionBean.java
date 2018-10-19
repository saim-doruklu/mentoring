package com.mentoring.managebeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

import com.mentoring.daos.AnswerDao;
import com.mentoring.daos.QuestionDao;
import com.mentoring.entities.Answer;
import com.mentoring.entities.category;
import com.mentoring.entities.question;

@ManagedBean(name = "questionBean")

@SessionScoped

public class QuestionBean implements actions  {

	@ManagedProperty(value = "#{questionDao}")
	private QuestionDao questionDao;
	
	List<question> questions;
	
	@ManagedProperty(value = "#{answerDao}")
	private AnswerDao answerDao;
	  
	@ManagedProperty(value = "#{categoryBean}")
	private CategoryBean categoryBean;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private Answer answer = new Answer();
	
	private question question = new question();
	
	private List<question> allQuestions;
	
	private UploadedFile file  ;
	

	@PostConstruct
	public void init() {
	
		if(loginBean.getUser().getRoli().getName().equals("admin"))
		
		allQuestions = questionDao.getQuestion();
		
		questions = questionDao.getQuestionUser(loginBean.getUser().getId());	
			
	}

	public List<question> getQuestions() {
		
		questions = questionDao.getQuestionUser(loginBean.getUser().getId());
		
      	return questions;
	}
	

	public void setQuestions(List<question> questions) {
	
		this.questions = questions;
	}

	public QuestionDao getQuestionDao() {
	
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		
		this.questionDao = questionDao;
	}

	public AnswerDao getAnswerDao() {
	
		return answerDao;
	}

	public void setAnswerDao(AnswerDao answerDao) {
	
		this.answerDao = answerDao;
	}
	
	public CategoryBean getCategoryBean() {
	
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
	
		this.loginBean = loginBean;
	}
	
	public question getQuestion() {
	
		return question;
	}
	
	public Answer getAnswer() {
	
		return answer;
	}
	
	public void setAnswer(Answer answer) {
	
		this.answer = answer;
	}

	public void setQuestion(question question) {
	
		this.question = question;
	}
	
	
	public void setAllQuestions(List<question> allQuestions) {
		
		this.allQuestions = allQuestions;
	}
	
	public List<question> getAllQuestions() {
		
		allQuestions = questionDao.getQuestion();
		
		return allQuestions;
	}
	
	
    public UploadedFile getFile() {
	
		return file;
	}

	public void setFile(UploadedFile file) {
	
		this.file = file;
	}
	

	public void add(){
		
		if(file.getSize() != 0){
			
			if (new File("C:/Users/iNTECO/git/quiz-maker/quiz-maker/WebContent/resources/image", file.getFileName()).exists())
				
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Change the name of the file or choose another!"));
			}
			
			else{
			
				if ((answer.getTrue1().equals(answer.getFalse1())) || (answer.getTrue1().equals(answer.getFalse2())) || (answer.getTrue1().equals(answer.getFalse3()))||(answer.getFalse1().equals(answer.getFalse2()))||(answer.getFalse1().equals(answer.getFalse3()))||(answer.getFalse2().equals(answer.getFalse3())))
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "The answered you entered should not be the same ! Reset! "));
				}
				else{
				
					question.setImage(file.getFileName());
					System.out.println(file.getFileName());
					File destination = new File("C:/Users/iNTECO/git/quiz-maker/quiz-maker/WebContent/resources/image", file.getFileName());
				
					try {
					
						InputStream in = file.getInputstream();
						OutputStream out = new FileOutputStream(destination);
						int read = 0;
						byte[] bytes = new byte[1024];
						
						while ((read = in.read(bytes)) != -1) {
							out.write(bytes, 0, read);}
							in.close();
							out.flush();
							out.close();
							
							} 
					catch (IOException e) 
						{
					// TODO Auto-generated catch block
						e.printStackTrace();
						}
						
					question.setName(question.getName());
				
					question.setCategory(categoryBean.getCategoryDao().get(categoryBean.getId()));
			
					question.setUseri(loginBean.getUser());
			
					questionDao.add(question);
			
					answer.setQuestion(question);
			
					answerDao.add(answer);
			
					question = new question();
			
					answer = new Answer();
			
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The question was sucessfully added"));
			
					categoryBean.setId(0);
			
					questions = questionDao.getQuestionUser(loginBean.getUser().getId());	
			
					if(loginBean.getUser().getRoli().getName().equals("admin"))
			
					allQuestions = questionDao.getQuestion();	
				
					}		
				}
			}
			
		else
		{
			if( (answer.getTrue1().equals(answer.getFalse1())) || (answer.getTrue1().equals(answer.getFalse2())) || (answer.getTrue1().equals(answer.getFalse3()))||(answer.getFalse1().equals(answer.getFalse2()))||(answer.getFalse1().equals(answer.getFalse3()))||(answer.getFalse2().equals(answer.getFalse3())))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "The answered you entered should not be the same ! Reset! "));
			}
			else
			{
				
				question.setName(question.getName());
			
				question.setCategory(categoryBean.getCategoryDao().get(categoryBean.getId()));
		
				question.setUseri(loginBean.getUser());
		
				questionDao.add(question);
			
				answer.setQuestion(question);
			
				answerDao.add(answer);
			
				question = new question();
			
				answer = new Answer();
				
				file = null;
			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The question was sucessfully added"));
			
				categoryBean.setId(0);
			
				questions = questionDao.getQuestionUser(loginBean.getUser().getId());	
			
				if(loginBean.getUser().getRoli().getName().equals("admin"))
			
				allQuestions = questionDao.getQuestion();
			}	
		}
	}
	
	
	
	public String update(){
		
		if(file.getSize() != 0 ){
			
			
			if (new File("C:/Users/iNTECO/git/quiz-maker/quiz-maker/WebContent/resources/image", file.getFileName()).exists())
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Change the name of the file or choose another!"));
				return null;
			}
			else{
				if((question.getAnswer().getTrue1().equals(question.getAnswer().getFalse1())) || (question.getAnswer().getTrue1().equals(question.getAnswer().getFalse2())) || (question.getAnswer().getTrue1().equals(question.getAnswer().getFalse3()))||(question.getAnswer().getFalse1().equals(question.getAnswer().getFalse2()))||(question.getAnswer().getFalse1().equals(question.getAnswer().getFalse3()))||(question.getAnswer().getFalse2().equals(question.getAnswer().getFalse3()))){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning", "The answered you entered should not be the same ! Reset! "));
					return null;
				
				}
				else
				{
				
					question.setImage(file.getFileName());
				
					File destination = new File("C:/Users/iNTECO/git/quiz-maker/quiz-maker/WebContent/resources/image", file.getFileName());
					try
					{
						InputStream in = file.getInputstream();
						OutputStream out = new FileOutputStream(destination);
						int read = 0;
						byte[] bytes = new byte[1024];
						while ((read = in.read(bytes)) != -1) {
							out.write(bytes, 0, read);}
							in.close();
							out.flush();
							out.close();	
					} 
					catch (IOException e) 
					{
					// TODO Auto-generated catch block
							e.printStackTrace();
					}
					question.setCategory(categoryBean.getCategoryDao().get(categoryBean.getId()));
					   
					questionDao.update(question);
				
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The question was sucessfully updated"));
				
					question =  new question();
				
					categoryBean.setId(0);
					
					file=null;
			 
					questions = questionDao.getQuestionUser(loginBean.getUser().getId()) ;	
				
					if(loginBean.getUser().getRoli().getName().equals("admin"))
				
						allQuestions = questionDao.getQuestion();
				
					if(loginBean.getUser().getRoli().getId()==1)
					
						return "question";
				
					else
						
						return "userpage";
				
				}
			
			}
			
		}
		else
		{
			if( (question.getAnswer().getTrue1().equals(question.getAnswer().getFalse1())) || (question.getAnswer().getTrue1().equals(question.getAnswer().getFalse2())) || (question.getAnswer().getTrue1().equals(question.getAnswer().getFalse3()))||(question.getAnswer().getFalse1().equals(question.getAnswer().getFalse2()))||(question.getAnswer().getFalse1().equals(question.getAnswer().getFalse3()))||(question.getAnswer().getFalse2().equals(question.getAnswer().getFalse3()))){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "The answered you entered should not be the same ! Reset! "));
				return null;
			
			}
			else
			{
		
				question.setCategory(categoryBean.getCategoryDao().get(categoryBean.getId()));
				   
				questionDao.update(question);
			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The question was sucessfully updated"));
			
				question =  new question();
			
				categoryBean.setId(0);
				
				file=null;
		 
				questions = questionDao.getQuestionUser(loginBean.getUser().getId()) ;	
			
				if(loginBean.getUser().getRoli().getName().equals("admin"))
			
					allQuestions = questionDao.getQuestion();
			
				if(loginBean.getUser().getRoli().getId()==1)
				
					return "question";
			
				else
					
					return "userpage";
			}
		}
		
	}
	
	public void delete(int question) {
		
		if(questionDao.get(question).getImage()!= null)
		{
			File file = new File("C:/Users/CCS/git/quiz-maker/quiz-maker/WebContent/resources/image/"+questionDao.get(question).getImage());
		file.delete();
		}
		
		questionDao.delete(question);
		
		questions.remove(questionDao.get(question));
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "The question was sucessfully deleted"));
		
		questions = questionDao.getQuestionUser(loginBean.getUser().getId());
		
		if(loginBean.getUser().getRoli().getName().equals("admin"))
		
		allQuestions = questionDao.getQuestion();	
		
		}
				

	public String select(int question)
	{
			
		this.question = questionDao.get(question);
			
		List<category> cs = categoryBean.getCategories();
			
		for(int i=0; i<cs.size();i++)
		{
				
			if(cs.get(i).getId()==this.question.getCategory().getId())
			{
						
				category c = cs.get(i);
						
				cs.set(i,  categoryBean.getCategories().get(0));
						
				cs.set(0, c);
			}	
				
		}
			
	categoryBean.setCategories(cs);
			
		categoryBean.setId(this.question.getCategori().getId());
			
		if(loginBean.getUser().getRoli().getId()==1)
				
			return "questionUpdateAdmin";
			
		else
					
			return "questionUpdate";
				
	}
		

	public String view(int question){
		
		this.question = questionDao.get(question);
		
		return "questionView";
		
	}
	
	public String turnBack(){
		
		question = new question();
		
		return "adminpage";
	}
	
	public String getPage(){
		question = new question();
		categoryBean.setId(0);
		answer = new Answer();
		if(loginBean.getUser().getRoli().getId()==1)
			return "question?faces-redirect=true";
		else
			return "userpage";
	}
}
