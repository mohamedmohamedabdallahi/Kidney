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
			
    public static double proba(double a , double  b){
    	double r = Math.random();
    	System.out.println(r);
    	double m = a - (a-b)/2;
    	if(r<m) {
    		r=a;
    	}
    	else {
    		r=b;
    	}
    	return r;
    }
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
	
	public static LinkedList<Integer>[] Groupe(int n) {
		String []Patient = blood(n);
		String []Doner = blood(n);
		LinkedList<Integer> []L = new LinkedList[30];
		for (int i =0;i<Patient.length;i++) {
			L[i] = new LinkedList<Integer>();
			for(int j =0;j<Doner.length;j++) {
				if(Patient[i].equals(Doner[j])) {
					L[i].add(j);
				}}
				if(!L[i].contains(i)) {
					double r = Math.random();
					if(r<0.5) {
						L[i].add(i);
					}
					else L[i].add(-1);
				}
				Collections.shuffle(L[i]);
				
			}
	return L;
	}
	public static Map<ArrayList<HashSet<Integer>>, LinkedList<Integer>[]> algoDirDon(int n) {
		LinkedList<Integer>[] L = Groupe(n);
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
	public static Map<int[][], LinkedList<Integer>[]>alogKidEx(int n){
		LinkedList<Integer>[] L = Groupe(n);
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
	/*
	Map<int[][], LinkedList<Integer>[]> preferences1 = Simulation.alogKidEx(30);
	for (Map.Entry<int[][], LinkedList<Integer>[]> pair : preferences1.entrySet()) {
		int [][]preferences2 = pair.getKey();
	    LinkedList<Integer>[] blood = pair.getValue();
	
	
	String rule;
	//rule = "A";
 rule = "B";  // if you choose rule B uncomment this line
	int[] matchnigList = KidExchange.match(preferences2, rule);
	
	KidExchange.printMatching(matchnigList);
	for(int m =0;m<30;m++) {
		System.out.println("les voisin de"+m+"sont :");
		for(int t:blood[m]) {
			System.out.println(t+",");
		}
	} 
	}*/
	
}
