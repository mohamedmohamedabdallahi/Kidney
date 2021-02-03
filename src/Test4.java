import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test4 {
	
	/*
	 *  To test the algorithm implementation of Question 4
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// ______ Reading the file _______
		
		Scanner sc = new Scanner(new File("ExempleQ4.text"));
		//sc.useDelimiter("[\\p{javaWhitespace}\\p{Punct}]+");
		String current = null;
		int n = Integer.parseInt(sc.next()) ; 
		int [][] preferences  = new int [n][];
		int i = 0;
		while (sc.hasNext()) {
			sc.next(); // sauter une ligne
			String row = sc.next();
			List<String> myList = new ArrayList<String>(Arrays.asList(row.split(",")));
			int rowlength = myList.size(); 
			preferences[i] = new int[rowlength] ;
			for (int j = 0; j < rowlength; j++) {
				preferences[i][j] = Integer.parseInt(myList.get(j)) - 1; 
			}
			i++;
		}
		sc.close();
		
		//  ______ End Reading ______
		
		
		
		String rule;
		rule = "A";
	 // rule = "B";  // if you choose rule B uncomment this line
		int[] matchnigList = KidExchange.match(preferences, rule);
		KidExchange.printMatching(matchnigList);
	
	
	
}}
