package test;

public class Action extends Movie {
	public Action(int id,String rating,String title) {
		super(id,rating,title);
	}
	public void setFee() {
		fee = 3;
	}
}