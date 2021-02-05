import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Test1 {
	
	public static void main(String[] args) throws FileNotFoundException {
	
	// ______ Reading Test file _______
	
			Scanner sc = new Scanner(new File("ExempleQ4.text"));
			//sc.useDelimiter("[\\p{javaWhitespace}\\p{Punct}]+");
			String current = null;
			int n = Integer.parseInt(sc.next()) ; 
			
			ArrayList<HashSet<Integer>> preferences = new ArrayList<HashSet<Integer>>();
			int i = 0;
			while (sc.hasNext()) {
				sc.next(); // sauter une ligne
				String row = sc.next();
				List<String> myList = new ArrayList<String>(Arrays.asList(row.split(",")));
				int rowlength = myList.size(); 
				HashSet<Integer> preferenceCol = new HashSet<Integer>() ;
				for (int j = 0; j < rowlength; j++) {
					preferenceCol.add(Integer.parseInt(myList.get(j)) - 1); 
				}
				preferences.add(preferenceCol);
				i++;
			}
			sc.close();
			
	//  ______  Reading End ______
	
			
			//int[] matchnigList = DirectDon.match(preferences);
			//DirectDon.printMatching(matchnigList); 
			ArrayList<HashSet<Integer>> l=  Simulation.algoDirDon(30);
			int[] matchnigList = DirectDon.match(l);
			DirectDon.printMatching(matchnigList); 
			
	
	
	}

}
