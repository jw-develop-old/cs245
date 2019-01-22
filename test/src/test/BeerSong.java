package test;

public class BeerSong {
	private int b;
	public BeerSong(int b) {
		if (b<0)
			this.b = 0;
		else if (b>99)
			this.b = 99;
		else
			this.b = b;
	}
	public void printSong() {
		for (int i = b;i > 0;i--) {
			System.out.println(i + " bottles of beer on the wall,");
			System.out.println(i + " bottles of beer");
			System.out.println("Take one down, pass it around,");
			System.out.println((i-1) + " bottles of beer on the wall.");
		}
	}
	public static void main(String[] args) {
		BeerSong song1 = new BeerSong(5);
		song1.printSong();
	}
}
