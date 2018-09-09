package managebeans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import daos.QuestionDao;
import entities.question;

@ManagedBean(name = "quizBean")
@ViewScoped
public class QuizBean {


	@ManagedProperty(value = "#{questionDao}")
	private QuestionDao questionDao;
	
	private List<entities.question> questions;
	
	private HashMap<question,String[]> quests = new HashMap<question,String[]>();
	
	private HashMap<question, String> selects = new HashMap<question, String> ();
	
	private HashMap <question,String> messages = new HashMap<question,String>();
	
	private int points;
	



	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		
		questions = questionDao.createQuiz();
		quests = fill(quests);
		
	}

	public List<entities.question> getQuestions(){
		questions = questionDao.createQuiz();
		return questions;
	}

	public void setQuestions(List<entities.question> questions){
		this.questions = questions;
	}
	
	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao){
		this.questionDao = questionDao;
	}
	

	public HashMap<question, String[]> getQuests() {
		return quests;
	}

	public void setQuests(HashMap<question, String[]> quests){
		this.quests = quests;
	}

	
	public HashMap<question, String> getSelects() {
		return selects;
	}

	
	public void setSelects(HashMap<question, String> selects) {
		this.selects = selects;
	}
	
    
	
	public int getPoints() {
		points = llogaritPiket();
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public HashMap<question, String> getMessages() {
		
		return messages;
	}

	public void setMessages(HashMap<question, String> messages) {
		this.messages = messages;
	}

	
	

	@SuppressWarnings("rawtypes")
	public int llogaritPiket(){
    	int piket = 0; 
    	Set set = selects.entrySet();
    	Iterator iterator = set.iterator();
    	
    	while(iterator.hasNext()){
    		
    		Map.Entry mentry = (Map.Entry)iterator.next();
    		
    		if (((entities.question) mentry.getKey()).getAnswer().getTrue1().equals(mentry.getValue()))
    		{ 
    			piket++; 
    			messages.put((question) mentry.getKey(), "Right Choice");
    		
    		}
    		else
    		{
    			messages.put((question) mentry.getKey(), "Wrong! Right answer : "+((entities.question) mentry.getKey()).getAnswer().getTrue1());
    		}
    		
    	}
    	return piket;
    }

    public String[] randomize(String[] array){
    	Random generator = new Random();
    	for (int i = 0;i<array.length;i++){
    		{
    			int rnr = generator.nextInt(array.length);
    			String m = array[i];
    			array[i] = array[rnr];
    			array[rnr] = m;
    			
    		}
    	}
		return array;
    }
    
    

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap fill(HashMap hm){
    	
    	for(int i=0; i <questions.size();i++){
			{
				String s1 = questions.get(i).getAnswer().getTrue1();
				String s2 = questions.get(i).getAnswer().getFalse1();
				String s3 = questions.get(i).getAnswer().getFalse2();
				String s4 = questions.get(i).getAnswer().getFalse3();
				String[] m = {s1,s2,s3,s4};
				m = randomize(m);
				question q = questions.get(i);
				hm.put(q, m);
			}
		}
		return hm;
    	
    }
	
}
