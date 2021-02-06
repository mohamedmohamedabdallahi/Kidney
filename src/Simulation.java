import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Simulation {
	static String O ="O" ;
	static String B ="B" ;
	static String AB  ="AB";
	static String A ="A";
			
 
	public static String [] blood(int n) {
		String[]L = new String[n];
    	for(int i =0;i<n;i++) {
    	double r = Math.random();
    	if(r<=0.04) {
    		L[i]=AB;
    	}
    	else if(r<0.15) {
    		L[i]=B;
    	}
    	else if(r<0.54) {
    		
    			L[i] = A;
    	}
    	else L[i] = O;}
    	return L;
    }
	
	public static LinkedList<Integer>[] Groupe(String []Patient , String []Doner) {
		int n = Patient.length;
		LinkedList<Integer> []L = new LinkedList[n];
		for (int i =0;i<Patient.length;i++) {
			L[i] = new LinkedList<Integer>();
			for(int j =0;j<Doner.length;j++) {
				if(Patient[i].equals(Doner[j])) {
					L[i].add(j);
				}}
			   Collections.shuffle(L[i]);
				if(!L[i].contains(i)) {
					double r = Math.random();
					if(r<0.5) {
						L[i].add(i);
					}
					else L[i].add(-1);
				}
			
				
			}
	return L;
	}
	public static Map<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> algoDirDon(LinkedList<Integer>[] L) {
		ArrayList<HashSet<Integer>> preferences = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < L.length; i++) {
			HashSet<Integer> preferenceCol = new HashSet<Integer>() ;
			for(int j : L[i]) {
			preferenceCol.add(j); 
			}
			preferences.add(preferenceCol);
		}
		Map<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> coordinates = new HashMap<>();
		coordinates.put( preferences,L);
		
		return coordinates;
	}
	public static Map<int[][], LinkedList<Integer>[]>alogKidEx(LinkedList<Integer>[] L){
        int n =L.length;
		int [][] preferences = new int[n][];
		for (int i = 0; i < L.length; i++) {
			preferences[i] = new int[L[i].size()];
			int k =0;
			for(int j : L[i]) {
					preferences[i][k]=j;
					k++;
				}
				
			}
		Map<int[][], LinkedList<Integer>[]> coordinates = new HashMap<>();
		coordinates.put( preferences,L);
	
		return coordinates;
	}
	
	public static LinkedList<Integer> waitingList(int[]L) {
		LinkedList<Integer> waintinglist= new LinkedList<Integer>();
		int w =0;
		for(int i =0;i<L.length;i++) {
			if(L[i]==-1) { 
				waintinglist.add(i);
				}
		}
		return waintinglist;
	}
	public static int wiatinglistmatch(LinkedList<Integer> waitinglist,String [] cadavre,String [] Patient) {
		int n =0;
		for(int i : waitinglist) {
			for(int j =0;j<cadavre.length;j++) {
			if(Patient[i].equals(cadavre[j])) {
				n++;
				cadavre[j]=null;
				break;
			}
				}
		}
		return n;
	}
	public static int nbrtransplatation(int []L , int w) {
		int n = 0;
		LinkedList<Integer> wait=  waitingList(L);
		n = L.length - wait.size() + w;
		return n;
	}
	
	/*
	 int n = 5
	LinkedList<Integer>[] L = Simulation.Groupe(n);
		Map<int[][], LinkedList<Integer>[]> preferences1 = Simulation.alogKidEx(L);
		for (Map.Entry<int[][], LinkedList<Integer>[]> pair : preferences1.entrySet()) {
			int [][]preferences2 = pair.getKey();
		    LinkedList<Integer>[] blood = pair.getValue();
		
		
		String rule;
		rule = "A";
	 //rule = "B";  // if you choose rule B uncomment this line
		int[] matchnigList = KidExchange.match(preferences2, rule);
		
		KidExchange.printMatching(matchnigList);
		for(int m =0;m<n;m++) {
			int k=m+1;
			System.out.println("les voisin de"+k+"sont :");
			for(int t:blood[m]) {
				int r =t+1;
				System.out.println(r+",");
			}
		} 
		}
		}
	} 
	}*/
	
}
