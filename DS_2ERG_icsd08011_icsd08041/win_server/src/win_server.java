//2 omadiki
//icsd08011-icsd08041

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.rmi.NotBoundException;

public class win_server extends UnicastRemoteObject implements diepafi_2{
       public diepafi look_op;//dilosi metavliton
   String game_server ="//Localhost/game_server";
    
    boolean lock=false;
    String x="O nikitis den katachorithike";
    String y="O nikitis den vrethike";
    String winner;
     int gameNumber = 0;
    ArrayList<String>username;
    ArrayList<String>Winner;
    Winner_user w1=new Winner_user(6,"athina","2222");
   
public win_server () throws RemoteException , NotBoundException, MalformedURLException{// constractor
 super();
  look_op=(diepafi)Naming.lookup(game_server);
  
     username = new ArrayList<String>();
     Winner = new ArrayList<String>();
     username.add("athina");//pernoume tis parametrous gia ta stoixeia ton pelaton
     Winner.add("2222");
     username.add("maria");
     Winner.add("1111");
 }

   //@Override
public synchronized String Katachorisi_Nikiti(String user_name){// methodos gia nea eggrafi
        if(lock==true){// an einai true
            try {
                wait();// perimenoun oi ipolipi kai gia anagnosi kai gia eggrafi
            } catch (InterruptedException ex) {
                Logger.getLogger(win_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
           lock=true;// an perasei apo ton pano elexo dld den grafei kapios allos tote tin kano true
           
           
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(999999);//dimiourgo ena tixaio arithmo
        String num=Integer.toString(randomInt);
        username.add(user_name);// perno to username
        Winner.add(num);// kai ton tixaio aritho 
        x="O nikitis katachorithike me arithmo: "+num;// minima gia epistrofi 
        
        lock=false;// kano false tin metavliti oste na mporoun na diavasoun kai na gra4oun oi ipolipoi xristes 
        notifyAll();// tous idopio
        
        
        return x;
        
}  
        
      
public synchronized String Current_Nikitis(String NIKITIS){// methodos gia anazitisi afm 
         gameNumber++;
         if(lock==true){// an grafei kapios tote perimenei to notify
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(win_server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           }
        lock=true;
        
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(999999);//dimiourgo ena tixaio arithmo
        String num=Integer.toString(randomInt); 
        
    for (int i= 0;i<Winner.size();i++){
       if (Winner.get(i).equals(NIKITIS)){
         String name=username.get(i);  
          y="O Prwtos nikitis vrethike me username: "+name; //minima gia epistrofi
          
           lock=false;
           notifyAll();
        }
       
       }
     return y;
             
     
         }
 
 
  
public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
   try{
            win_server server_1=new win_server ();
            Naming.rebind("//localhost/win_server", server_1);//dilonoume tin diefthinsi stin opoia perimenei to kalesma
        }
        catch (RemoteException | MalformedURLException e) {
            System.out.println("win_server err: " + e);
            System.exit(1);
        }
     
}
}