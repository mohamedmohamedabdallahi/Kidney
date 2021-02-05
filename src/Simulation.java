import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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
	public static void main(String[] arg) {
		LinkedList<Integer>[] L = Groupe(30);
		for(int i : L[1]) {
			System.out.println(i);
		}
	}
}
