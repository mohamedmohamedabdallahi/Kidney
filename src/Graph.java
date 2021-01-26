
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
public class Graph<V> {

  protected Map<V, LinkedList<V>> adj;
  
  public Graph() {
    this.adj = new HashMap<V, LinkedList<V>>();
  }
  
  public void addVertex(V x) {
    LinkedList<V> l = this.adj.get(x); 
    if (l == null) this.adj.put(x, new LinkedList<V>());
  }
  
  void removeVertex(V x) {
    this.adj.remove(x);
    for (V v: vertices())
      this.adj.get(v).remove(x);
  }
  
  public boolean hasEdge(V x, V y) {
    LinkedList<V> l = this.adj.get(x);
    return l != null && l.contains(y);
  }
  
  public void addEdge(V x, V y) {
    addVertex(x);
    this.adj.get(x).add(y);
  }
  
  public void removeEdge(V x, V y) {
    LinkedList<V> l = this.adj.get(x);
    if (l != null) l.remove(y);
  }

  public Set<V> vertices() {
    return this.adj.keySet();
  }
  
  public LinkedList<V> successors(V v) {
    return this.adj.get(v);
  }
  
  public void makeUndirected() {
    for (V u : this.adj.keySet())
        for (V v : this.adj.get(u))
          this.addEdge(v, u);
  }
  
  public Graph<V> reverse() {
  	Graph<V> r = new Graph<V>();
  	for (V u : this.adj.keySet())
  		r.addVertex(u);
 	  for (V u : this.adj.keySet())
      for (V v : this.adj.get(u))
        r.addEdge(v, u);
  	return r;
  }
}

class Edge<V> {

  V src, dst;

  Edge(V src, V dst) {
    this.src = src;
    this.dst = dst;
  }
}

class TestGraph {
  public static void main(String[] args) {
    Graph<Integer> g = new Graph<Integer>();
    g.addEdge(1, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 4);
    for( int i : g.successors(1)) {
    	System.out.println(i);
    }
    
    
  }
}