import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test13 {
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
		
		
		///test-----------
		
		
	        /*int patien = 30;
	        String []Patient = Simulation.blood(patien);
			String []Doner = Simulation.blood(patien);
			LinkedList<Integer>[] L = Simulation.Groupe(Patient ,Doner );
			String[] cadavre = Simulation.blood(3);
				Map<int[][], LinkedList<Integer>[]> preferences1 = Simulation.alogKidEx(L);
				for (Map.Entry<int[][], LinkedList<Integer>[]> pair : preferences1.entrySet()) {
					int [][]preferences2 = pair.getKey();
				    LinkedList<Integer>[] blood = pair.getValue();
				
				
				String rule;
				rule = "A";
			 //rule = "B";  // if you choose rule B uncomment this line
				int[] matchnigList = KidExchange.match(preferences2, rule);
				
				//KidExchange.printMatching(matchnigList);
				for(int m =0;m<patien ;m++) {
					int k=m+1;
					//System.out.println("les voisin de"+k+"sont :");
					for(int t:blood[m]) {
						int r =t+1;
						//System.out.println(r+",");
					}
					
				
				}LinkedList<Integer>	waitinglist = Simulation.waitingList(matchnigList);
				for(int p : waitinglist) {
					System.out.println("patiant "+p+" pour une blood "+Patient[p]); 
				}
				for(int b = 0 ;b<cadavre.length;b++) {
					System.out.println("Kidney "+b+" pour une blood "+cadavre[b]); 
				}
				int nbr = Simulation.wiatinglistmatch(waitinglist, cadavre, Patient);
				int nbrtrnsp = Simulation.nbrtransplatation(matchnigList, nbr);
				System.out.println("nbre de trnspl "+nbrtrnsp);
				}System.out.println("nouveau");
				Map<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> preferences3 = Simulation.algoDirDon(L);
				for (Map.Entry<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> pair : preferences3.entrySet()) {
					ArrayList<HashSet<Integer>>preferences2 = pair.getKey();
				    LinkedList<Integer>[] blood = pair.getValue();
				    int[] matchnigList = DirectDon.match(preferences2);
					
					//DirectDon.printMatching(matchnigList);
					for(int m =0;m<patien ;m++) {
						int k=m+1;
						//System.out.println("les voisin de"+k+"sont :");
						for(int t:blood[m]) {
							int r =t+1;
							//System.out.println(r+",");
						}
					} 
					//System.out.println("Nombre de transplation pour directdonation est : "+Simulation.nbrtransplation(matchnigList, 3));	
					LinkedList<Integer>	waitinglist = Simulation.waitingList(matchnigList);
					for(int p : waitinglist) {
						System.out.println("patiant "+p+" pour une blood "+Patient[p]); 
					}
					for(int b = 0 ;b<cadavre.length;b++) {
						System.out.println("Kidney "+b+" pour une blood "+cadavre[b]); 
					}
					int nbr = Simulation.wiatinglistmatch(waitinglist, cadavre, Patient);
					int nbrtrnsp = Simulation.nbrtransplatation(matchnigList, nbr);
					System.out.println("nbre de trnspl "+nbrtrnsp);
					}*/System.out.println("reel test ");
					int patien = 30;
					int s =0;
					 double s1 =0;
					for(int y =0 ;y<100;y++) {
						String [] Patient = Simulation.blood(patien);
						String [] Doner = Simulation.blood(patien);
						LinkedList<Integer>[] L = Simulation.Groupe(Patient ,Doner );
						String []cadavre = Simulation.blood(3);
						/*Map<int[][], LinkedList<Integer>[]>preferences1 = Simulation.alogKidEx(L);
						for (Map.Entry<int[][], LinkedList<Integer>[]> pair : preferences1.entrySet()) {
							int [][]preferences2 = pair.getKey();
						    LinkedList<Integer>[] blood = pair.getValue();
						
						
						String rule;
						rule = "A";
					 //rule = "B";  // if you choose rule B uncomment this line
						int[] matchnigList = KidExchange.match(preferences2, rule);
						LinkedList<Integer>waitinglist = Simulation.waitingList(matchnigList);
						int nbr = Simulation.wiatinglistmatch(waitinglist, cadavre, Patient);
						int nbrtrnsp = Simulation.nbrtransplatation(matchnigList, nbr);
						s=s+nbrtrnsp;
					}s1 = (double)s/100;*/
					Map<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> preferences3 = Simulation.algoDirDon(L);
					for (Map.Entry<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> pair : preferences3.entrySet()) {
						ArrayList<HashSet<Integer>>preferences2 = pair.getKey();
					    LinkedList<Integer>[] blood = pair.getValue();
					    int[] matchnigList = DirectDon.match(preferences2);
					    LinkedList<Integer>waitinglist = Simulation.waitingList(matchnigList);
						int nbr = Simulation.wiatinglistmatch(waitinglist, cadavre, Patient);
						int nbrtrnsp = Simulation.nbrtransplatation(matchnigList, nbr);
						s=s+nbrtrnsp;
					}s1 = (double)s/100;
					    
						
					
					}
					System.out.println("Moyen pour Direct algo est "+s1);
				
			
             
             }
}
