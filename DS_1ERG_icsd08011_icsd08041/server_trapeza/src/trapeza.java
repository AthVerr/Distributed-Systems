
public class trapeza {
    
      int arithmos_log;      
    double balance; 
    
    
    
     public trapeza(int ar,double yp){         
            
       arithmos_log=ar;  
       balance=yp;
    }
     
     
         double get_Ypoloipo(){return  balance;}
  int get_arithmos_log(){return arithmos_log;}
     
     
//katathesi
public void deposit(double xrimata) {
	balance = balance + xrimata;
System.out.println("to neo ypoloipo sas einai"+balance);
    }


  public class TransException extends Exception {
    public static final long serial_user_ID = 331003;
    public TransException(String s) {
	super(s);
    }
}
//analipsi
    public void withdraw(double xrimata) throws trapeza.TransException {
	if (balance >= xrimata) {
	    balance = balance - xrimata;
	} else {
	    throw new trapeza.TransException("H analipsi ksepernaei to upoloipo.");
	}
    }
      
   
//elegxos ypoloipou
    public void balance() {
System.out.println("to ypoloipo sas einai"+balance);
    }
     
}
