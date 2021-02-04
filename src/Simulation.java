
public class Simulation {
	static String O ="O" ;
	static String B ="B" ;
	static String AB  ="AB";
	static String A ="A";
			
    public static double proba(double a , double  b){
    	double r = Math.random();
    	System.out.println(r);
    	double m = a - (a-b)/2;
    	if(r<m) {
    		r=a;
    	}
    	else {
    		r=b;
    	}
    	return r;
    }
	public static String [] blood(int n) {
		String[]L = new String[n];
    	for(int i =0;i<n;i++) {
    	double r = Math.random();
    	if(r<=0.04) {
    		L[i]=AB;
    	}
    	else if(r<0.15) {
    		L[i]=B;
    	}
    	else if(r<0.54) {
    		
    			L[i] = A;
    	}
    	else L[i] = O;}
    	return L;
    }
	
	public static void main(String[] args) {
		String []Patient = blood(100);
		String []Doner = blood(100);
		for (int i =0;i<Patient.length;i++) {
	System.out.println((Patient[i]+","+Doner[i]));}
	}
}
