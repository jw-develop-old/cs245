package linecounter;

import java.io.*;
import java.util.*;

/**
 * Class to count the lines and characters in Internal and SetUp,
 * with and without comments.
 * @author sirjwhite
 */
public class Counter {
	
	private static String[] files = {
		"NCInternal.txt","NCSetUp.txt",
		"Internal.java","SetUp.java"
	};
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		int nocomments = counter.count("linecounter/"+files[0])+
				counter.count("linecounter/"+files[1]);
		int comments = counter.count("calc/"+files[2])+
				counter.count("calc/"+files[3]);
		System.out.printf("\nWith Comments:\n%s\n",comments);
		System.out.printf("\nWithout Comments:\n%s\n",nocomments);
	}
	
	private int count(String file) {
		Scanner input = makeStream(file);
		int count;
		for (count = 0;input.hasNext();count++)
			input.nextLine();
		System.out.println(file + ":");
		System.out.printf("%s\n\n",count);
		return count;
	}
	
	private Scanner makeStream(String filename) {
		InputStream stream = 
				this.getClass().getClassLoader().getResourceAsStream(filename);
		return new Scanner(stream);
    }
}
