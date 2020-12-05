package dto;

public class ReportedIncidentsByExpert {

	private int id;
	private String incDesc;
	private String postedDate;
	private String expertID;
	private String customerID;
	private int questionID;
	private int answerID;
	
	
	// Getter And Setter Automatic  --------------------------------------------------------

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public String getExpertID() {
		return expertID;
	}
	public void setExpertID(String expertID) {
		this.expertID = expertID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getIncDesc() {
		return incDesc;
	}
	public void setIncDesc(String incDesc) {
		this.incDesc = incDesc;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getAnswerID() {
		return answerID;
	}
	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}
}
