//1 omadiki
//icsd08011-icsd08041

import java.io.*;
import java.net.*;
import java.util.*;

public class AuctionHouse implements Runnable{
    private Socket connection;
    private String message,msg,read="",user,pass,name,timi_ek,bid,timi_curr;
    private boolean available = true, authenticated=false;
    static ArrayList<User> Users=new ArrayList<User>();
         Item a=new Item("kt",400); 
    AuctionHouse(Socket s) {
        this.connection = s;
    }
     
    public static void main(String args[]){
        try{
            //ftiaxnw 2 xristes
            Users.add(new User("user","pass"));
            Users.add(new User("user1","pass1"));
            // Dhmiourgia tou server socket
            ServerSocket providerSocket = new ServerSocket(5555);
            System.out.println("Waiting for connection...");
            
            while(true){
                Socket connection = providerSocket.accept();    //Dexetai th sundesh apo ton client
                Runnable runnable = new AuctionHouse(connection);     //Dhmiourgw ena runnable antikeimeno tou server kai to anathetw se ena thread
                Thread thread = new Thread(runnable);
                thread.start();                         //ksekinaw thn run()
            }     
        }catch(Exception e){}
    }
    
    
    public synchronized void doProsfora()throws  InterruptedException{     
        
         while(available==false){//otan to available einai false 
            wait();//tote ola ta thread pou theloun na kanoun eggrafi sto arxeio mpenoun se anamoni
           }
  available=false;
  
        try{ 
         //kalei tin item   
           
             // a.prosfora(pr); gia na parei timi apo grafika
             
              a.prosfora(); //tin methodo gia ton ypologimo prosforas
               a.timi_current();  //tin methodo gia tin teliki timi
            
          available=true;//allazo tin katastasi tou available
     notifyAll();// mporoun na sinexeisoun oloi
   }
     //ekserese an dn mporei na ginei eggrafi sto arxeio
        catch (Exception ex){
            System.err.println("Error during writing the file: " + ex.getMessage());
                }            
      }
    
    
    
    public void run(){
        try{
            System.out.println("Connection received from "+connection.getInetAddress().getHostName());
            //Input kai Output streams gia thn epikoinwnia me ton client
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            
            message = "Waiting authentication... ";
            out.writeObject(message);               //apostolh mhnmumatos epituxhs sundeshs
            out.flush();
            System.out.println("server> " + message);
            user=(String)in.readObject();           //diavazei username
            System.out.println("server> " + user);
            pass=(String)in.readObject();           //diavazei password
            System.out.println("server> " + pass);
            for(int i=0;i<Users.size();i++){       //psaxnw sto Database tou Array gia to an uparxei o xristis me ta stoixeia p exw valei
                if(Users.get(i).getUsername().equals(user) && Users.get(i).getPassword().equals(pass)){
                    authenticated=true;
                }
            }
            out.writeObject(authenticated);               //apostolh mhnmumatos epituxhs sundeshs
            out.flush();//apodesmeusi mnimis
            //an authentikopoithei o xristis
            if(authenticated==true){
               
                   //diavazei ta stoixei p stelnei o client apo to grafiko
            System.out.println("onoma proiontos " + name);
          name=(String)in.readObject(); 
           System.out.println(" timi ekkinisis" + timi_ek);
            timi_ek=(String)in.readObject(); 
              System.out.println(" prosfora" + bid);
            bid=(String)in.readObject();
          //   float  pr = Float.parseFloat( bid); gia na parei timi apo grafika
             System.out.println(" teliki timi" +timi_curr);
           timi_curr=(String)in.readObject();
            

           
       doProsfora(); 
       String s=  a.toString(); 
             out.writeObject(s);
              out.flush();
              
              //afou steilei tin prosfora kleinei tin syndesi
              in.close();
                out.close();
                connection.close(); 
                     System.out.println("Connection Closing...");
     
              
              
            }
            else{
                in.close();
                out.close();
                connection.close();
                  System.out.println("Connection Closing...");
            }
            
           
        }catch(Exception e){}
    }
}
