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
				if(Patient[i].equals(AB)) {
					L[i].add(j);
				}
				else if(Patient[i].equals(B)) {
					if(Doner[j].equals(B) || Doner[j].equals(O)) {
					 L[i].add(j);
					}
				}
				else if(Patient[i].equals(A)) {
					if(Doner[j].equals(A) || Doner[j].equals(O)) {
					 L[i].add(j);
					}
				}
				else if(Patient[i].equals(O)) {
					if(Doner[j].equals(O)) {
					 L[i].add(j);
					}
				}
				}
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
	public static ArrayList<HashSet<Integer>>algoDirDon(LinkedList<Integer>[] L) {
		ArrayList<HashSet<Integer>> preferences = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < L.length; i++) {
			HashSet<Integer> preferenceCol = new HashSet<Integer>() ;
			for(int j : L[i]) {
			preferenceCol.add(j); 
			}
			preferences.add(preferenceCol);
		}
		
		return preferences;
	}
	public static int[][] alogKidEx(LinkedList<Integer>[] L){
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
		
		return preferences;
	}
	public static int[][]alogGreedy(LinkedList<Integer>[] L){
        int n =L.length;
		int [][] Matadj = new int[n][n];
		for (int i = 0; i < L.length; i++) {
			for(int j =0 ;j<n ; j++) {
				if(L[i].contains(j) && L[j].contains(i)) {
				Matadj[i][j]=j;
				}
				else {Matadj[i][j]=0;
				}
				
			}
				}
		
		return Matadj;
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
	public static void printSumilation() {
		int patien = 30;
		double s = 0;       //nombre de transpaltation pour Cycle and chain
		double s1 = 0;       //nombre de transpaltation pour Direct Donation
		for(int y = 0 ;y < 100;y++) {
			String [] Patient = Simulation.blood(patien);    //Generer 30 patient 
			String [] Doner = Simulation.blood(patien);       //Generer 30 patient 
			LinkedList<Integer>[] L = Simulation.Groupe(Patient ,Doner );  // retourn liste de preference de chaque patient 
			String []cadavre = Simulation.blood(3);  //Generer 3 Kidney 
			int[][] SumKidEX = Simulation.alogKidEx(L);  
			ArrayList<HashSet<Integer>> SumDirect = Simulation.algoDirDon(L);
			
			String rule;
			rule = "A";
		 //rule = "B";  // if you choose rule B uncomment this line
			int[] matchnigList = KidExchange.match(SumKidEX, rule);
			LinkedList<Integer>waitinglist = Simulation.waitingList(matchnigList);
			int nbr = Simulation.wiatinglistmatch(waitinglist, cadavre, Patient);
			int nbrtrnsp = Simulation.nbrtransplatation(matchnigList, nbr);
			s=s+nbrtrnsp;
		
		    int[] matchnigList1 = DirectDon.match(SumDirect);
		    LinkedList<Integer>waitinglist1 = Simulation.waitingList(matchnigList1);
			int nbr1 = Simulation.wiatinglistmatch(waitinglist1, cadavre, Patient);
			int nbrtrnsp1 = Simulation.nbrtransplatation(matchnigList1, nbr1);
			s1=s1+nbrtrnsp1;
		}s1 = (double)s1/100;
		s = (double)s/100;
		    
			
		
		System.out.println("Moyen pour KidExchange est "+s);
		System.out.println("Moyen pour Direct algo est "+s1);
	
	}
	
	
}
