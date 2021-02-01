import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;



 class KidExchange {
	

	 private static int[][] preferences;
	 
	  static int[] match(int[][] preferences) {
		    int size = preferences.length;
	        int[] matches = new int[size];
	        int[] prefIndexes = new int[size];
			boolean[] KidneysInWchainIndexes = new boolean[size];
			boolean[] PatientsInWchainIndexes = new boolean[size];
	        HashSet<Integer> freePatient = new HashSet<>();
	        for (int i = 0; i < size; i++) {
	            matches[i] = -2; 
	            prefIndexes[i] = 0;
	            freePatient.add(i);
	        }
	        boolean isTheEnd = false;
	        while(!isTheEnd) {
	            for(int x : freePatient) {
	            	int topChoice = preferences[x][prefIndexes[x]];

	                while (topChoice!= -1 && !PatientsInWchainIndexes[x] && (!freePatient.contains(topChoice) || KidneysInWchainIndexes[topChoice])) {
	                    prefIndexes[x]++;
	                    topChoice = preferences[x][prefIndexes[x]];
	                }
	                matches[x] = topChoice;
	            }
	            CycleOrChain SelectedChainOrCycle = getCycleOrChainB(matches, freePatient, PatientsInWchainIndexes);
	            
	            
	            
	  
	            
	            
	            if (SelectedChainOrCycle == null) {
	            	isTheEnd = true;
	            }
	            
	            
	            
	            else {
	            	if(SelectedChainOrCycle.type.equals("cycle")) {            		
	            		for (int x : SelectedChainOrCycle.elements) {
	            			freePatient.remove(x);
	            		}  	
	            	}
	            	else if (SelectedChainOrCycle.type.equals("wchain")) {
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
	    	
	       /* # Takes a preference matrix, a vector indicating which patients have already been matched and one indicating which have already been assigned to the waiting list
	    	  # Go through all individuals and try to find a cycle or a chain
	    	  # If a cycle is found, return it and stop
	    	  # If a chain is found, check if it is longer than all previous found chains, and start with the next individual and try to find a longer chain
	    	  # Return the cycle or the longest chain as the second element of a list, first element indicates whether it is a cycle or chain
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
	            		
	            		//we select this chain if "cycle" is longuer than longuestChain or both have the same size and the "cycle" is more prior
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
	    	
		       /* # Takes a preference matrix, a vector indicating which patients have already been matched and one indicating which have already been assigned to the waiting list
		    	  # Go through all individuals and try to find a cycle or a chain
		    	  # If a cycle is found, return it and stop
		    	  # If a chain is found, check if it is longer than all previous found chains, and start with the next individual and try to find a longer chain
		    	  # Return the cycle or the longest chain as the second element of a list, first element indicates whether it is a cycle or chain
		    	*/
		    	
		        HashSet<Integer> SelectedChain = new HashSet<>();
		        Queue<Integer> highestPriorityChaine = new PriorityQueue<>();
		        
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
			            	
		            	if (highestPriorityChaine.size() == 0 || compairPiriority(PeriorityCycle, highestPriorityChaine) ) {
		            		
		            		//we select this chain if "cycle" is longuer than longuestChain or both have the same size and the "cycle" is more prior
		            		SelectedChain = cycle;
		            		highestPriorityChaine =  PeriorityCycle;
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
	    
	    public static void printTheMatching(int[] matches) {
	    	
	    	
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
	 
	 
	 public static void main(String [] args) {
	 }
 }
