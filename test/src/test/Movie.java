package test;

public abstract class Movie {
	protected int id;
	protected int days;
	protected double fee = 2;
	private String rating;
	private String title;
	public Movie(int id,String rating,String title) {
		setid(id);
		setrating(rating);
		settitle(title);
	}
	public int getid() {
		return id;
	}
	public String getrating() {
		return rating;
	}
	public String gettitle() {
		return title;
	}
	public void setid(int id) {
		this.id = id;
	}
	public void setrating(String rating) {
		this.rating = rating;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public boolean equals(Movie obj1,Movie obj2) {
		if (obj1.id == obj2.id)
			return true;
		return false;
	}
	public double calcLateFees(int days) {
		return fee*days;
	}
	public abstract void setFee();
	public String toString() {
		return "Film is called " + title + " " + id + " " +rating + " ";
	}
	public static void main(String[] args) {
		Drama lotr = new Drama(12167,"Pg-13","Lord of the Rings");
		Action matrix = new Action(42,"R","The Matrix");
		System.out.println(matrix.toString());
		System.out.println(lotr.toString());
	}
}