package test;

public class HotDogStand {
	private int id;
	private static int sold;
	public HotDogStand(int id) {
		this.id = id;
		sold = 0;
	}
	public static void justSold() {
		sold++;
	}
	public static int getSold() {
		return sold;
	}
	public static void main(String[] args) {
		HotDogStand jims = new HotDogStand(44);
		HotDogStand babs = new HotDogStand(2);
		HotDogStand lijs = new HotDogStand(1800);
		for (int i = 1;i<=25;i++) {
			HotDogStand.justSold();
			System.out.println("We have sold "+HotDogStand.sold+" hotdogs!");
		}
	}
}
