import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class InfaisablePath {
	
	public static LinkedList<Path> pathes (int [][] adjMatrix, int K) {
			int n  = adjMatrix.length;
			int counter = 1; // path length
			ArrayList<LinkedList<Path>> pathsList = new ArrayList<>();
			ArrayList<LinkedList<Path>> pathsListHolder = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				pathsListHolder.add(new LinkedList<Path>());
				pathsListHolder.get(i).add(new Path(i));
			}
			
			while (counter != K) {
				pathsList = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					pathsList.add(new LinkedList<Path>());
					for (int j = 0; j < n; j++) {
						if (adjMatrix[i][j] == 1) {
							for (Path P : pathsListHolder.get(j)) {
								pathsList.get(i).add(new Path(i, P));
								
							}
						}
					}
				}
				
				pathsListHolder = pathsList; 
				counter++;
			}
			
			LinkedList<Path>  allKpaths = new LinkedList<>();
			for (LinkedList<Path> L : pathsList ) {
				for (Path path : L ) {
					allKpaths.add(path);
				}
			}
			return allKpaths;
			
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
	Path (int i){
		this.path = new LinkedList<Integer>();
		this.path.add(i);
	}
	Path(int i, Path P){
		this.path = new LinkedList<Integer>();
		this.path.add(i);
		for (int j : P.path) {
			this.path.add(j);
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