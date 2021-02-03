
import java.util.LinkedList;

public class Kidney {
	
	Pair[] allPairs;
	public final static Doner w = new Doner(-1);
	
	
	
	Kidney (int [][] preferences) {
		
		int n = preferences.length;
		this.allPairs = new Pair[n];
		for (int i = 0; i < n; i++) {
			this.allPairs[i] = new Pair(new Doner(i), new Patient(i, null));
		}
		
		for (int i = 0; i < n; i++) {
			LinkedList<Doner> preference = new LinkedList<Doner>();
			
			for (int j : preferences[i]) {
				if (j == -1) {
					preference.add(w);
				}
				else {
				preference.add(allPairs[j].k);
				}
			}
			allPairs[i].t.preference = preference;
		}
	}
	
	
	
	boolean check(Doner elt, Patient t) {
		
		/*
		 * check if the doner is inside the preference list of its patient
		 */
		
		for ( Doner k : t.preference) {
			if(elt.equal(k)) return true;
			
		}
		return false;
	}
	
	// implement direct donation algorithm 
	Pair [] DirectDonation() {
		Pair[] MatchingList = new Pair[allPairs.length] ;
		
		for( Pair P : allPairs) {
			if (check(P.k, P.t)) {
				MatchingList[P.t.id] = P;
			}
			else {
				MatchingList[P.t.id] = new Pair(w, P.t);
			}
		}
		
		return MatchingList;
	}
	
}
