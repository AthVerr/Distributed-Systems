//2 omadiki
//icsd08011-icsd08041

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;


public class game_server extends UnicastRemoteObject implements diepafi{

      String win_server ="//Localhost/win_server";//die8finseis ton serner
      
    boolean answer=false;
    String apantisi;
    String poso;
      private Stack announcedBalls;
           
      diepafi_2 look_op1;
    
public  game_server () throws RemoteException, NotBoundException, MalformedURLException {
 
 
look_op1=(diepafi_2 ) Naming.lookup(win_server);// dinoume tin dieufinsi gia epikinonia me ton server

}
    
    String kwdikoi[][]={{"athina","2222"},{"maria","1111"}};
    
    
      public boolean authentication(String username ,String pin) throws RemoteException{// methodos gia a8fentikopoihsi
        
        for(int i=0;i<2;i++){
            if(kwdikoi[i][0].equals(username)){
               
                if(kwdikoi[i][1].equals(pin)){
                    
                     answer=true;
                     }
                }
        }
        return answer;
      }
    
        
         public Card[] deltia(long seed) throws RemoteException{// methodos gia a8fentikopoihsi
        
      
       
           Card[] cards = new Card[3];
            Random generator = new Random(seed);

	    for (int i = 0; i < 3; i ++){
	        cards[i] = new Card(generator);
                
                }
            
            return   cards;
            
        }
        
           
         public BingoBall announceNextBalls() throws RemoteException{
             
       Random generator = new Random(System.currentTimeMillis());
       Vector balls = new Vector(BingoBall.MAX);
	BingoBall returnThis;

        
       for (int j = BingoBall.MIN; j <= BingoBall.MAX; j++){
	    balls.addElement(new BingoBall(j));          
       }
	    int num = (int)(generator.nextDouble() * balls.size());
	  returnThis = (BingoBall)balls.elementAt(num);
	    balls.removeElementAt(num);
	return    returnThis;
           
		
          
    }
         
         public boolean verify(Card c)throws RemoteException{// methodos gia a8fentikopoihsi
        
        int colMatches = 0;
	int rowMatches = 0;
	int diagMatches = 0;
	int otherDiagMatches = 0;

 announcedBalls=  new Stack();
announcedBalls.push(new BingoBall(BingoBall.FREE_SPACE));

	for (int i = 0; i < Card.SIZE; i ++) {
	    for (int j = 0; j < Card.SIZE; j ++) {
		if (announcedBalls.contains(c.boardValues[i][j]))
		    rowMatches ++;
		if (announcedBalls.contains(c.boardValues[j][i]))
		    colMatches ++;
		if ((i == j) && announcedBalls.contains(c.boardValues[i][j]))
		    diagMatches ++;
		if (((i + j) == (Card.SIZE-1)) && announcedBalls.contains(c.boardValues[j][i]))
		    otherDiagMatches ++;
	    }
	    if (colMatches == Card.SIZE) {
		return true;
	    } else if (rowMatches == Card.SIZE) {
		return true;
	    } else {
		rowMatches = 0;
		colMatches = 0;
	    }
	}
	if (diagMatches == Card.SIZE) {
	    return true;
	} else if (otherDiagMatches == Card.SIZE) {
	    return true;
	}

	return false;
    
    }
    
         public void close()throws RemoteException{//methodos gia klisimo
    
    answer=false;
    
    }
    
    
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
       
              try {     
           // RMISecurityManager security = new RMISecurityManager();
           // System.setSecurityManager(security);
           
    game_server server=new game_server();
           
 Naming.rebind("//localhost/game_server", server);
              }
              
              catch (Exception e) {
            System.out.println("rmi_server err: " + e);
            System.exit(1);
    
}
              
              
        
    }
}
