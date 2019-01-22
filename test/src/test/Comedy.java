package test;

public class Comedy extends Movie {
	public Comedy(int id,String rating,String title) {
		super(id,rating,title);
	}
	public void setFee() {
		fee = 2.5;
	}
}
