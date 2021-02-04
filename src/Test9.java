import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test9 {
	
	
	public static void main(String [] args) throws FileNotFoundException {
	// ______ Reading Test file _______
	
				Scanner sc = new Scanner(new File("test1.txt"));
				//sc.useDelimiter("[\\p{javaWhitespace}\\p{Punct}]+");
				String current = null;
				
				int n =  new BigDecimal(sc.next()).intValue();
				int k = 3;
				new BigDecimal(sc.next()).intValue(); 
				int [][] adj  = new int [n][n];
				int i = 0;
				while (sc.hasNext()) {
					for (int j = 0; j < n; j++) {
						adj[i][j] =   new BigDecimal(sc.next()).intValue(); 
					}
					i++;
				}
				sc.close();
				
		//  ______  Reading End ______
				/*
				for (int m = 0; m < n; m++) {
					for (int s = 0; s < n; s++) {
						System.out.print(""+adj[m][s] +"");
					}
					
					System.out.println();
				}
				*/
				
				long tempsDebut = System.currentTimeMillis();
				LinkedList<Path> allKpaths = InfeasiblePath.pathes(adj, k);
				long tempsFin = System.currentTimeMillis();
				long ExecutionTime = (tempsFin - tempsDebut);
				double seconds = ExecutionTime / 1000F;
				System.out.println("Operation performed in : "+ ExecutionTime + " CPUtime.");
			    System.out.println("Which corresponds to : "+ Double.toString(seconds) + " seconds.");
				System.out.println();
				System.out.println();
				InfeasiblePath.printPaths(allKpaths);
	}

}
