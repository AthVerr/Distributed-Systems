//3 omadiki 
//icsd08041-icsd08011

import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable{  //dimiourgia thread
    private boolean available = true;
    String message;
    ArrayList<String> files=new ArrayList<String>(); //ftiaxnw arraylist gia na apothikeuw ta arxeia
    
    Thread t;
    Client(){ //constructor
        files.add("f1"); //prosthetw arxeia
        files.add("f2");
        files.add("f3");
        files.add("f4");
        files.add("f5");
        t=new Thread(this,"P2P client");
        System.out.println("P2P client thread: "+t+" created");
        t.start();    
    }
    
    public void run(){ //kyriws programma
        try{
            Socket requestSocket = new Socket("192.168.56.1",5555);    //Dimiourgia kai kateuthinsi tou socket sto localhost
            System.out.println("Connected to "+requestSocket.getInetAddress()+" in port "+requestSocket.getPort());

            ObjectOutputStream out = new ObjectOutputStream(requestSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());
            
            message = (String) in.readObject();         //lipsi minimatos epituxis sundesis
            System.out.println("server> " + message);
        
            out.writeObject(files);             //apostoli arraylist
            out.flush();
            
            message="request";
            out.writeObject(message);           //apostoli request
            out.flush();
            System.out.println("client> "+message);
            
             synchronized (this) {
                while (available == false) {//otan to available einai false 
                    wait();//tote ola ta thread  mpenoun se anamoni
                }
                available = false;
                try {
                    ArrayList<String> tmpFiles = (ArrayList) in.readObject();    //lipsi arithmou arxeiwn p leipoun
                    System.out.println("server> missing files send");

                    for (int i = 0; i < tmpFiles.size(); i++) {   //kanei tous duo pinakes 1
                        files.add(tmpFiles.get(i).toString());
                    }
                    available = true;//allazw tin katastasi tou available
                    notifyAll();//idopoiw oti mporoun na sinexisoun oloi

                } //ekseresi 
                catch (Exception ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }     
            message="release";
            out.writeObject(message);  //apostoli release
            out.flush();
            System.out.println("client> "+message);
            
            out.close();
            in.close();
            requestSocket.close();
        }catch (Exception ex){System.err.println("Caught exception " + ex.toString());}
    }
    
    public static void main(String[] args) {
        
        //an eixame arxeio kai oxi arrylist
        /* try{
            new File("Files").mkdir();  
            new File("Files/f1.txt").createNewFile();
            new File("Files/f2.txt").createNewFile();
            new File("Files/f3.txt").createNewFile();
            new File("Files/f4.txt").createNewFile();
            new File("Files/f5.txt").createNewFile();
        }catch (Exception ex){System.err.println("Caught exception " + ex.toString());}*/
        
        
        new Client();
    }
}