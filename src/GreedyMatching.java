
public class GreedyMatching {
	
	public static int [] match(int [][] adj) {
		int n = adj.length;
		int [] matches = new int [n];
		for (int i = 0; i < n; i++) {
			if (adj[i] == null) continue; 
			for (int j : adj[i]) {
				if (adj[j] != null) {
					matches[i] = j;
					matches[j] = i;
					adj[i] = null;
					adj[j] = null;
					break;
				}
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
