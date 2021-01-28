import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Test2 {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("KidneyMatching.text"));
		//sc.useDelimiter("[\\p{javaWhitespace}\\p{Punct}]+");
		String current = null;
		int n = Integer.parseInt(sc.next()) ; 
		int [][] K  = new int [n][];
		int i = 0;
		while (sc.hasNext()) {
			current = sc.next();
			int j = Integer.parseInt(current); 
			K[i] = new int[j] ;
			for (int s = 0; s < j; s++) {
				K[i][s] = Integer.parseInt(sc.next());
			}
			i++;
		}
		sc.close();
		
		
		
		LinkedList<Pair> MatchingList = Kidney.GreedyMatching( K ) ;
		
		for (Pair P : MatchingList) {
			System.out.println(P);
		}
	}
	
	
	
}
