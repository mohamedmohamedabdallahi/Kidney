import java.util.ArrayList;
import java.util.HashSet;

public class DirectDon {
	static int[] match(ArrayList<HashSet<Integer>> preferences) {
		int n = preferences.size();
		int[] matches = new int [n]; // for a patient i, matches[i] is the index of the kidney assigned to him or -1 for w (waiting list)
		for (int i = 0; i<n; i++) {
			if (preferences.get(i).contains(i)) {
				matches[i] = i;
			}
			else {
				matches[i] = -1;
			}
		}
		return matches;
	}
	
	public static void printMatching(int[] matches) {
		
    	for (int i = 0; i < matches.length; i++) {
    		int j = i+1;
    		if (matches[i] == -1) {
    			
    			System.out.println("The patient " + j + " registered in the waiting list");
    		}
    		else {
    			int m = matches[i]+1;
    			System.out.println("The patient " + j + " is assigned to the kidney " + m);
    		}
    	}
    }


}
