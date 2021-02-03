import java.util.LinkedList;

class Patient {
	int id;
	LinkedList<Doner> preference;
	
	Patient(int id, LinkedList<Doner> prefernce){
		this.id = id;
		this.preference = prefernce;
		
	}
}

class Doner{
	int id;
	Doner(int id){
		this.id = id;
	}
	
	boolean equal(Doner d) {
		return d.id == this.id;
	}
	
	@Override
	public boolean equals(Object o) {
		Doner t = (Doner) o;
		return this.id == t.id;
	}
	
	@Override 
	public int hashCode() { 
		   int i = this.id;
		   i ^= (i << 13);
		   i ^= (i >>> 17);        
		   i ^= (i << 5);
		   return i;   
		}
	}
	
	

	
class Pair{
	Patient t;
	Doner k;
	
	Pair(Doner k, Patient t){
		this.t = t;
		this.k = k;
	}
	
	public String toString () {
		int i = this.t.id + 1 , j = this.k.id + 1;
		if (this.k.id == - 1) {	
		    return "The patient " + i + " registered in the waiting list";
	}
		else {
			return "The patient " + i+ " is assigned to the kidney " + j;
		}
}
}
