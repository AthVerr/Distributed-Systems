import java.io.*;
import java.net.*;
import java.util.*;      




public class trapeza_thread implements Runnable{
    private Socket sock;
    private String message,msg,read="",user,pass,name,timi_ek,bid,timi_curr;
    private boolean available = true, authenticated=false;
    static ArrayList<User> Users=new ArrayList<User>();
         
    trapeza_thread(Socket s) {
        this.sock = s;
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
                Runnable runnable = new trapeza_thread(connection);     //Dhmiourgw ena runnable antikeimeno tou server kai to anathetw se ena thread
                Thread thread = new Thread(runnable);
                thread.start();                         //ksekinaw thn run()
            }     
        }catch(Exception e){}
    }
    



  double balance;
    trapeza a=new trapeza(22,500);
 
 public synchronized void doDeposit()throws  InterruptedException{     
        
         while(available==false){//otan to available einai false 
            wait();//tote ola ta thread pou theloun na kanoun eggrafi sto arxeio mpenoun se anamoni
           }
  available=false;
  
        try{      
            a.deposit(500);            
          available=true;//allazo tin katastasi tou available
     notifyAll();// mporoun na sinexeisoun oloi
   }
     //ekserese an dn mporei na ginei eggrafi sto arxeio
        catch (Exception ex){
            System.err.println("Error during writing the file: " + ex.getMessage());
                }            
      }


 public synchronized void doWithdrawal()throws  InterruptedException{     
        
         while(available==false){//otan to available einai false 
            wait();//tote ola ta thread pou theloun na kanoun eggrafi sto arxeio mpenoun se anamoni
           }
  available=false;
  
        try{ 
    
            a.withdraw(600);       
          available=true;//allazo tin katastasi tou available
     notifyAll();// mporoun na sinexeisoun oloi
   }
     //ekserese an dn mporei na ginei eggrafi sto arxeio
        catch (Exception ex){
            System.err.println("Error during writing the file: " + ex.getMessage());
                }            
      }
 public  void  doBalance(){a.balance();}   
    
  
public void run(){
    
    
    try{     
        
        System.out.println("Connection received from "+sock.getInetAddress().getHostName());
            //Input kai Output streams gia thn epikoinwnia me ton client
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            
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
               
                     String strin ;   
                
                   strin=(String)in.readObject();
                 if(strin.equals("deposit")){
             //ektupwnetai to minima tou client
             System.out.println("The client says: "+strin);
             doDeposit(); //kalei tin mehodo  WriteTheFile
             }
                      strin=(String)in.readObject();
               if (strin.equals("withdraw")){
             //ektupwnetai to minima tou client
             System.out.println("The client says: "+strin);
          doWithdrawal(); 
             }
           strin=(String)in.readObject();
             if(strin.equals("balance")){
             //ektupwnetai to minima tou client
             System.out.println("The client says: "+strin);
          doBalance();
             }
          
              
              //afou steilei tin prosfora kleinei tin syndesi
              in.close();
                out.close();
               sock.close(); 
                     System.out.println("Connection Closing...");
     
              
              
            }
            else{
                in.close();
                out.close();
              sock.close();
                  System.out.println("Connection Closing...");
            }
            
           
        }catch(Exception e){}
    }
}

	  
                
          