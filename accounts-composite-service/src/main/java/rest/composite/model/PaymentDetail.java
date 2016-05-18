package rest.composite.model;
import java.util.Date;

public class PaymentDetail {
	
	private double amountDue;
	private double interestDue;
	private Date dueDate;
	private String text;

	public PaymentDetail(){}
	
	public PaymentDetail(double amountDue, double interestDue, Date dueDate, String text){
		this.amountDue = amountDue;
		this.interestDue = interestDue;
		this.dueDate = dueDate;
		this.text = text;
	}
	
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public double getInterestDue() {
		return interestDue;
	}
	public void setInterestDue(double interestDue) {
		this.interestDue = interestDue;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
