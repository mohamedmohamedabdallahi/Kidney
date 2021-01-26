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
}
	
class Pair{
	Patient t;
	Doner k;
	
	Pair(Doner k, Patient t){
		this.t = t;
		this.k = k;
	}
	
	public String toString () {
		if (this.k.id == - 1) {
		     return "The patient " + this.t.id + " registered in the waiting list";
	}
		else {
			return "The patient " + this.t.id + " is assigned to the kidney " + this.k.id;
		}
}
}
