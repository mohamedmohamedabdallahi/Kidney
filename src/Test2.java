import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test2 {

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
			
			
			
		
			int[] matchnigList = GreedyMatching.match(preferences);
			
			
			
			GreedyMatching.printMatching(matchnigList);
			
			// debug ///////////////
		/*
			LinkedList<Integer>[] Undirected =  GreedyMatching.preferncesToUndirctedGraph(preferences);
			int g = 1;
			for ( LinkedList<Integer> ListAdjI : Undirected) {
				System.out.print("peatient "+ g + " : ");
				for (int j : ListAdjI ) {
					System.out.print((j+1) + " , ");
				}
				System.out.println();
				g++;
			}*/
			/////////////
	}

}
