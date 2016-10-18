//3 omadiki 
//icsd08041-icsd08011

import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {   //dimiourgia thread

    ServerSocket providerSocket;
    boolean works = true;
    boolean isUsed = false;
    private boolean available = true;
    ArrayList<String> files = new ArrayList<String>();//ftiaxnw arraylist gia na apothikeuw ta arxeia
    private Socket connection;
    private int ID;
    Thread t;

    Server(Socket s, int i) {//constructor
        //kanei tin sindesi  

        this.connection = s;
        this.ID = i;

        //prosthetw arxeia
        files.add("f1");
        files.add("f2");
        files.add("f3");
        files.add("f4");
        files.add("f5");
        files.add("f6");
        files.add("f7");
        files.add("f8");
        files.add("f9");
        files.add("f10");
        files.add("f11");
        files.add("f12");
        files.add("f13");
        files.add("f14");
        files.add("f15");

    }

    public void run() { //kyriws programma
        try {




            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());

            String message = "Connection established!";
            out.writeObject(message);               //apostoli minimatos epituxis sundesis
            out.flush();
            System.out.println("server> " + message);
            Thread.sleep(3000); //koimatai gia 3 deuterolepta
            ArrayList<String> tmpFiles = files; //ftiaxnw neo array list
            ArrayList<String> userFiles = (ArrayList) in.readObject();//lipsi arraylist apo client

            //psaxnei sto array lista me mia for
            for (int i = 0; i < files.size(); i++) {
                for (int j = 0; j < userFiles.size(); j++) {
                    if (files.get(i).toString().equals(userFiles.get(j).toString())) {
                        for (int k = 0; k < tmpFiles.size(); k++) {
                            //edw ginetai i afairesh twn arxeiwn p exoun koina client k server
                            if (files.get(i).toString().equals(tmpFiles.get(k).toString())) {
                                tmpFiles.remove(k);
                            }
                        }
                    }
                }
            }

            message = (String) in.readObject();         //lipsi request
            System.out.println("client> " + message);
            Thread.sleep(1000); 

            synchronized (this) {
                while (available == false) {//otan to available einai false 
                    wait();//tote ola ta thread  mpenoun se anamoni
                }
                available = false;
                try {


                    out.writeObject(tmpFiles);   //apostoli arithmou arxeiwn p leipoun
                    out.flush();

                    System.out.println("server> missing files send");
                    available = true;//allazw tin katastasi tou available
                    notifyAll();//idopoiw oti mporoun na sinexisoun oloi

                } //ekseresi
                catch (Exception ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }

            message = (String) in.readObject();         //lipsh release
            System.out.println("client> " + message);

            Thread.sleep(1000); //koimatai gia ena lepto
            out.close();  //kleinei tin sindesi
            in.close();
            connection.close();
        } catch (Exception ex) {
            System.err.println("Caught exception " + ex.toString());
        }

    }

    //vazw tin main mesa sto thread
    public static void main(String[] args) { 

        int port = 5555;
        int count = 0;

        try {//kanei sindesi me client
            ServerSocket socket1 = new ServerSocket(port);
            System.out.println("MultipleSocketServer Initialized");
            System.out.println("Waiting Incoming Connection...");
            System.out.println("Local Address :" + socket1.getInetAddress() + " port:" + socket1.getLocalPort());

//ftiaxnei antikeimeno sto server p trexei oti einai sto tread me tin runnable

            Socket connection = socket1.accept();
            Runnable runnable = new Server(connection, ++count);
            Thread thread = new Thread(runnable);
            thread.start();   //arxizei to tread          
            socket1.close(); //kleinei to socket

        } catch (Exception e) {
        }
        {
        }

    }
}
