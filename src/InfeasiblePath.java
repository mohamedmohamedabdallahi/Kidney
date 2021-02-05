import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class InfeasiblePath {
	
	public static LinkedList<Path> pathes (int [][] adjMatrix, int K) {
/*
 * Search of all paths with length K+1 
 */

		int n  = adjMatrix.length;
		
		// we proceed by creating a list of buckets so that in the buckets of index i we store the paths starting with the vertex i
		ArrayList<LinkedList<Path>> currentPathsList  = new ArrayList<>();
		ArrayList<LinkedList<Path>> perviousPathsList  = new ArrayList<>();
		
		// Initialize paths with length 1
		for (int i = 0; i < n; i++) {
			perviousPathsList.add(new LinkedList<Path>());
			perviousPathsList.get(i).add(new Path(i));
		}
		
		
		int counter = 1;
		while (counter < K+1) {
			// Calculate paths with length counter+1 from paths with length counter;
			currentPathsList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				// for all vertex i
				currentPathsList.add(new LinkedList<Path>());
				for (int j = 0; j < n; j++) {
					if (adjMatrix[i][j] == 1) {
						// visit neighbors all  of vertex i
						for (Path P : perviousPathsList.get(j)) {
							//If  the path P don't contain i then concatenate i and Path  and store the result in currentPathsList
							if (!P.hsetPath.contains(i)) currentPathsList.get(i).add(new Path(i, P));
								
							}
						}
					}
				}
				
				perviousPathsList = currentPathsList; 
				counter++;
			}
			
			// group all paths into a single list
			LinkedList<Path>  allInfeasiblepaths = new LinkedList<>();
			for (LinkedList<Path> L : currentPathsList ) {
				for (Path path : L ) {
					allInfeasiblepaths.add(path);
				}
			}
			
			
		return allInfeasiblepaths;	
	}
	
	public static void printPaths(LinkedList<Path> pathes) {
		System.out.println(pathes.size() + " paths founded : " );
		System.out.println();
		for (Path P : pathes) {
			System.out.println(P);
		}
	}

}

class Path {
	
	LinkedList<Integer> path ;
	
	
	HashSet<Integer> hsetPath;
	
	
	Path (int i){
		this.path = new LinkedList<Integer>();
		this.hsetPath  = new HashSet<Integer>();
		this.path.add(i);
		this.hsetPath.add(i);
	}
	
	
	Path(int i, Path P){
		// to concatenate a vertex and path
		this.path = new LinkedList<Integer>();
		this.hsetPath  = new HashSet<Integer>();
		this.path.add(i);
		this.hsetPath.add(i);
		
		for (int j : P.path) {
			this.path.add(j);
			this.hsetPath.add(j);
		}
	}

	
	public String toString() {
		String S = " path : ";
		for (int index : this.path) {
			S += "-> "+ index +" ";
		}
		
		return S;	
	}
	
}
