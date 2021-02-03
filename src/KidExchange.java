import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;



 class KidExchange {
	

	 
	  static int[] match(int[][] preferences, String rule) {
		    int size = preferences.length;
	        int[] matches = new int[size];  
	        int[] prefIndexes = new int[size];   // for a patient i, it associate him to the index of his most preferred remaining unassigned kidney 
			boolean[] KidneysInWchainIndexes = new boolean[size];  // indicate true for the  assigned patients that where in w-chain 
			boolean[] PatientsInWchainIndexes = new boolean[size]; // indicate true for the assigned kidneys that where in w-chain
				
	        HashSet<Integer> freePatient = new HashSet<>(); // contains all patient that we didn't remove yet
	        for (int i = 0; i < size; i++) {
	            matches[i] = -2; 
	            prefIndexes[i] = 0;
	            freePatient.add(i);
	        }
	        boolean isTheEnd = false; // indicate the end of our procedure
	        while(!isTheEnd) {
	            for(int x : freePatient) {
	            
	            	
	            	int topChoice = preferences[x][prefIndexes[x]];

	                while (topChoice!= -1 && !PatientsInWchainIndexes[x] && (!freePatient.contains(topChoice) || KidneysInWchainIndexes[topChoice])) {
	                	/*
		            	 * for any patient that we didn't remove yet and he were not assigned and point to an unavailable kidney
		            	 *  -> we update the index of his most preferred remaining unassigned kidney
		            	 */
	                	prefIndexes[x]++;
	                    topChoice = preferences[x][prefIndexes[x]];
	                }
	                matches[x] = topChoice; // each remaining active patient points to his most preferred remaining unassigned kidney
	            }
	            
	            
	            // ----------------------
	            // * we detect a remaining cycle and if there is no cycle we find the most priority w-chain Using "getCycleOrChain" function
	            
	            CycleOrChain SelectedChainOrCycle;
	            if (rule.equals("A")) {
	            		
	            	  SelectedChainOrCycle = getCycleOrChainA(matches, freePatient, PatientsInWchainIndexes);
	            }
	           
	            else { 
	            	// we choose the rule B in this case
	            	
	            	 SelectedChainOrCycle = getCycleOrChainB(matches, freePatient, PatientsInWchainIndexes);
	            }
	            
	            // ----------------------
	            
	            
	            
	            if (SelectedChainOrCycle == null) {
	            	isTheEnd = true; // if there is no cycle  or w-chain that we didn't match yet then we end the procedure
	            }
	            
	            
	            
	            else {
	            	if(SelectedChainOrCycle.type.equals("cycle")) { 
	            		// if we detect a cycle then we match its elements and remove it from our graph
	            		for (int x : SelectedChainOrCycle.elements) {
	            			freePatient.remove(x);
	            		}  	
	            	}
	            	else if (SelectedChainOrCycle.type.equals("wchain")) {
	            		// if we detect a w chain then we match its elements and update the 
	            		for (int x : SelectedChainOrCycle.elements) {
	            			PatientsInWchainIndexes[x] = true;
	            			if (matches[x] == -1) continue;
	            			KidneysInWchainIndexes[matches[x]] = true;
	            		}
	            	}
	            } }
	        return matches;
	    }
	 

	    private static CycleOrChain getCycleOrChainA (int[] choices, HashSet<Integer> free, boolean[] PatientsInWchainIndexes) {
	    	
	       /* 
	        *  Takes a preference array, a vector indicating which patients have not already been removed and one indicating which have already been assigned and a part of w-chain
	    	*  Go through all individuals and try to find a cycle or a chain
	    	*  If a cycle is found, return it and stop
	    	*  If a chain is found, check if it is longer and have more priority than all previous found chains, and start with the next individual and try to find a longer chain
	    	*  Return the cycle or the more longer and priority chain as an object of CycleOrChain class, type attribute of this object indicates whether it is a cycle or chain
	    	*  Or return null if there is no cycle or w-chain;
	    	*/
	    	
	        HashSet<Integer> longuestChain = new HashSet<>();
	        Queue<Integer> PeriorityLonguestChain = new PriorityQueue<>();
	        
	        for (int x : free) {
	        	if (PatientsInWchainIndexes[x] ) {
	        		continue;
	        		}
	        	
	            HashSet<Integer> cycle = new HashSet<>();
	            Queue<Integer> PeriorityCycle = new PriorityQueue<>();
	            
	            cycle.add(x);
	            PeriorityCycle.add(x);
	            int choice = choices[x];
	            while (!cycle.contains(choice) || !free.contains(choice) ) {
	            	if( choice == -1 ) break;
	            	
	                cycle.add(choice);
	                PeriorityCycle.add(choice);
	                choice = choices[choice];
	            }
	            if (choice == x) {
	            	
	                return new CycleOrChain("cycle", cycle );
	            }
	            else if (choice == -1) {
		            	
	            	if (cycle.size() > longuestChain.size() ||
	            			( cycle.size() == longuestChain.size() && compairPiriority(PeriorityCycle, PeriorityLonguestChain)) ) {
	            		
	            		//we select this chain if "cycle" is longer than longuestChain or both have the same size and the "cycle" have priority
	            		longuestChain = cycle;
	            		PeriorityLonguestChain =  PeriorityCycle;
	                } 
	            	
	            }
	        }
	   
	        if (longuestChain.size() == 0) {
	        	return null;
	        
	        }
	        else {
	        	return new CycleOrChain("wchain", longuestChain);
	        }
	    }
	    
	    
	    
	    private static CycleOrChain getCycleOrChainB(int[] choices, HashSet<Integer> free, boolean[] PatientsInWchainIndexes) {
	    	
	       	 /* 
		        *  Takes a preference array, a vector indicating which patients have not already been removed and one indicating which have already been assigned and a part of w-chain
		    	*  Go through all individuals and try to find a cycle or a chain
		    	*  If a cycle is found, return it and stop
		    	*  If a chain is found, check if it is longer and have more priority than all previous found chains, and start with the next individual and try to find a longer chain
		    	*  Return the cycle or the more longer and priority chain as an object of CycleOrChain class, type attribute of this object indicates whether it is a cycle or chain
		    	*  Or return null if there is no cycle or w-chain;
		    	*/
		    	
		        HashSet<Integer> SelectedChain = new HashSet<>();
		        int  headOfSelectedChain = choices.length + 2; 
		        
		        for (int x : free) {
		        	if (PatientsInWchainIndexes[x] ) {
		        		continue;
		        		}
		        	
		            HashSet<Integer> cycle = new HashSet<>();
		            int headOfCycle = x ;
		            
		            cycle.add(x);
		            int choice = choices[x];
		            while (!cycle.contains(choice) || !free.contains(choice) ) {
		            	if( choice == -1 ) break;
		            	
		                cycle.add(choice);
		                choice = choices[choice];
		            }
		            if (choice == x) {
		            	
		                return new CycleOrChain("cycle", cycle );
		            }
		            else if (choice == -1) {
			            	
		            	if ( headOfCycle < headOfSelectedChain ) {
		            		
		            		//we select this chain if it start with patient that have more priority the the previous selected chain
		            		SelectedChain = cycle;
		            		headOfSelectedChain = headOfCycle;
		                } 
		            	
		            }
		        }
		   
		        if (SelectedChain.size() == 0) {
		        	return null;
		        
		        }
		        else {
		        	return new CycleOrChain("wchain", SelectedChain);
		        }
		    }
		    
		    
	    
	    
	    static boolean compairPiriority(Queue<Integer> Q1, Queue<Integer> Q2) {
	    	
	    	/*
	    	 * this function compare the priority of two w-chain
	    	 * In case there are two w-chains, we select the one with the highest priority patient.
	    	 * If the highest priority patient is part of  the both chains,
	    	 * select the chain with the second highest priority patient, and so on.
	    	 * if the selected chain is Q1 return true
	    	 */
	    	
	    	LinkedList<Integer> RemovedFromQ1 = new LinkedList<>();
	    	LinkedList<Integer> RemovedFromQ2 = new LinkedList<>();
	    	while(!Q1.isEmpty() && !Q2.isEmpty()) {
	    		int periority1 = Q1.poll();
	    		RemovedFromQ1.add(periority1);
	    		int periority2 = Q2.poll();
	    		RemovedFromQ2.add(periority2);
	    		
	    		if (periority1 < periority2) {
	    			for (int i : RemovedFromQ1) {
	    				Q1.add(i);
	    			}
	    			return true ;	
	    			
	    			
	    		}
	    		else if(periority1 > periority2){
	    			for (int i : RemovedFromQ2) {
	    				Q1.add(i);
	    			}
	    			return false;
	    		}
	    	}
	    	
	    	System.out.print("the two list has the same priority");
	    	return true;
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
 
 
 
 
 class CycleOrChain {
	public  String type ;
	public  HashSet<Integer> elements;
	 
	 CycleOrChain(String type, HashSet<Integer> elements){
		 this.type = type;
		 this.elements = elements;
	 }
	 
	 
	
 }
