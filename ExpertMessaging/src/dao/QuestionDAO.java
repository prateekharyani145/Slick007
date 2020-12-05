package dao;

import java.util.ArrayList;

import dto.Question;

public class QuestionDAO {

	public Question getQuestionById(int questionID) {
		Question question= null;
		try {
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return question;
	}
	
	public ArrayList<Question> getAllUnAnsweredQuestionsForCustomer(String customerID){
		ArrayList<Question> questionList= null;
		try {
			
		}
		catch(Exception ex) {
			
		}
		return questionList;
	}

}
