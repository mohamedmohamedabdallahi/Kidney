import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) throws FileNotFoundException {

	// ______ Reading the file _______
	
			Scanner sc = new Scanner(new File("ExempleQ2.text"));
			int n = Integer.parseInt(sc.next()) ; 
			int [][] adj  = new int [n][];
			int i = 0;
			while (sc.hasNext()) {
				sc.next(); // sauter une ligne
				String row = sc.next();
				List<String> myList = new ArrayList<String>(Arrays.asList(row.split(",")));
				int rowlength = myList.size(); 
				adj[i] = new int[rowlength] ;
				for (int j = 0; j < rowlength; j++) {
					adj[i][j] = Integer.parseInt(myList.get(j)) - 1; 
				}
				i++;
			}
			sc.close();
			
			//  ______ End Reading ______
			
			
			
		
			int[] matchnigList = GreedyMatching.match(adj);
			GreedyMatching.printMatching(matchnigList);
		
	}

}
