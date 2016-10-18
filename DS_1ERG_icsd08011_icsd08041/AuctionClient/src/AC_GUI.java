
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
 
//dimiourgia grafikou me to opoio kathe xristis tha eisagei ta aparaitita stoixeia gia tin epikoinwnia i 
//tha enimerwnete gia to proion pou dimoprateitai i gia tin timi pou exei ekeini tin stigmi
public class AC_GUI extends JFrame implements ActionListener{

    Socket requestSocket;
    String message;
    boolean authenitcated=false;

    Container pane;
    GridLayout layout2 = new GridLayout(4,1);
      GridLayout layout3 = new GridLayout(2,1);
    JPanel row1 = new JPanel(); 
    JLabel user = new JLabel("Username"); 
    JTextField userTF = new JTextField(15);
    JPanel row2 = new JPanel();
    JLabel pass = new JLabel("Password"); 
    JTextField passTF = new JTextField(15); 
    JPanel row3 = new JPanel();
    JButton connect = new JButton("Connect");
    JButton cancel = new JButton("Cancel");
    JPanel row4 = new JPanel(); 
    JLabel Bname = new JLabel("name of product");
    JTextField name = new JTextField(15);
    JPanel row5 = new JPanel(); 
     JLabel timi_ekk= new  JLabel("timi ekkinisis");
    JTextField timi_ek = new JTextField(15);
    JPanel row6 = new JPanel();
     JLabel timi_curren = new  JLabel("trexousa timi");
    JTextField timi_curr = new JTextField(15);
    JPanel row7 = new JPanel();
    JLabel Bid = new  JLabel("Prosfora");
    JTextField bid = new JTextField(15);
    JButton BUpdate = new JButton("Update");
    JPanel row8 = new JPanel();
    JButton analipsi = new JButton("analipsi");
    JButton katathesi = new JButton("katathesi");
    JPanel row9 = new JPanel();
    JButton erwtisi_upo = new JButton("erwtisi_upo");
    JTextField erwtisi_upol = new JTextField(15);
      

    AC_GUI() throws IOException, ClassNotFoundException {
        super("Authentication"); //titlos parathirou

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//gia na klino to parathiro
        setVisible(true);// emfanizo to parathiro
        pane = getContentPane();// dimiourgo ena antikeimeno gia na valo ta pedia sto parathiro 
        GridLayout layout = new GridLayout(6, 1);// dimourgo ena layout kai to vazo na emfanizete se 3 grammes kai 1 stili
        pane.setLayout(layout);//vazo ta layouts sto pane 

        row1.add(user);// vazo to lebel gia to onoma 
        row1.add(userTF);// vazo to textfield pou dino to onoma 
        pane.add(row1);//vazo ro row1 sto pane   

        row2.add(pass);
        row2.add(passTF);
        pane.add(row2);

        row3.add(connect);
        row3.add(cancel);
        pane.add(row3);

        row4.add(Bname);
        row4.add(name);
        //pane.add(row4);
        
        row5.add(timi_ekk);
        row5.add(timi_ek);
        //pane.add(row5);
        
        row6.add(timi_curren);
        row6.add(timi_curr);
        //pane.add(row6);

        row7.add(Bid);
        row7.add(bid);
        row7.add(BUpdate);
        //pane.add(row7);
        
        row8.add(analipsi);
        row8.add(katathesi);
        
        row9.add(erwtisi_upo);
        row9.add(erwtisi_upol);
        
        setContentPane(pane);
        pack();
        connect.addActionListener(this);  //koumpi gia syndesi sto authentikation
        BUpdate.addActionListener(this);//koumpi gia katathesi prosforas kai timwn sto parathiro
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent evet) {//ektelesi actionlistener
        Object source = evet.getSource();// dimiourgo ena antikeimeno etsi oste na paro to onoma tou gegonotos

        if (source == connect) {
            try {
                // 1. creating a socket to connect to the server
                Socket requestSocket = new Socket("127.0.0.1", 5555);    //Dhmiourgia kai kateuthunsh tou socket sto localhost
                System.out.println("Connected to localhost in port 2008");


                //Input kai Output streams gia thn epikoinwnia me ton server
                ObjectOutputStream out = new ObjectOutputStream(requestSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());

                message = (String) in.readObject();         //lhpsh mhnmumatos epituxhs sundeshs
                System.out.println("server> " + message);

                out.writeObject(userTF.getText());          //username
                System.out.println("client> " + userTF.getText());
                out.flush();
                out.writeObject(passTF.getText());          //pass
                System.out.println("client> " + passTF.getText());
                out.flush();
                
                authenitcated=(boolean)in.readObject();     //diavazei authenticated
                if(authenitcated==true){
                    System.out.println("server> Client authenticated");
                    
               //menu gia na epileksei o xristis ton server kai ti tha kanei   
      System.out.println("*****************************");
	System.out.println("(1) dimoprasies");
	System.out.println("(2) trapeza");	
	System.out.println("(3) Eksodos\n");
	System.out.print  ("Epilekste tin energeia sas: ");
    
         Scanner text_in = new Scanner(System.in);
	    String select = (String) text_in.nextLine();        
	    int i = Integer.parseInt(select,3);
              System.out.println("enimerwsi gia to epilogi xristi ");  
          
              
            switch(i) {
	case 1 :              
                   
             System.out.println("enimerwsi gia to antikeimeno se plistiriasmo ");         
                    //deutero parathiro grafikwn
                    pane.removeAll();
                    pane.setLayout(layout2);
                    pane.add(row4);
                    pane.add(row5);
                    pane.add(row6);
                    pane.add(row7);
                    revalidate();
                    repaint();
             
              //diavazei to ti grafw mesa sta JTEXTFIELD                    
                out.writeObject(name.getText());          //username
                System.out.println("onoma pwliseis" + name.getText());
                out.flush();
                out.writeObject(timi_ek.getText());          //pass
                System.out.println("timi ekkinisis" + timi_ek.getText());
                out.flush();
                  out.writeObject(bid.getText());          //pass
                System.out.println("prosfora " + bid.getText());
                out.flush();  
                 out.writeObject(timi_curr.getText());          //pass
                System.out.println("teliki timi " + timi_curr.getText());
                out.flush();  
                
                     
   //diavazei ti stelnei o server,diladi oti periexei to arxeio item stis dimoprasies
                  String  plirofories=(String)in.readObject();
               System.out.println("oi plirofories gia tin dimoprasia einai:" +plirofories);
               
               //kleinei tin syndesi
                 in.close();
               out.close();            
              System.out.println("Connection Closing...");
                    
        break;
     
        case 2 :  
                    System.out.println("epilogi trapeza ");   
            //epilogi gia server trapezas
            pane.removeAll();
                    pane.setLayout(layout3);
                    pane.add(row8);
                    pane.add(row9);
                    revalidate();
                    repaint();
             Object sv = evet.getSource();       
      
               if (sv == analipsi)
               {
                try {
	       
            out.writeObject("withdraw "+"\n");               //apostolh mhnmumatos epituxhs sundeshs
            out.flush();      
          
            
 } catch (Exception ex) {
            }
        }     
        
                if (sv == katathesi)
               {
                try {
	      
            out.writeObject(" deposit"+"\n");               //apostolh mhnmumatos epituxhs sundeshs
            out.flush(); 
            
 } catch (Exception ex) {
            }
        }   
             
   
                if (sv == erwtisi_upo)
               {
                try {
	  
            out.writeObject("balance "+"\n");               //apostolh mhnmumatos epituxhs sundeshs
            out.flush();          
          
            
 } catch (Exception ex) {
            }
        }   
       
        	  in.close();
               out.close();            
              System.out.println("Connection Closing...");	
	     break;
                
         default: {System.out.println("Invalid choice.  Please try again.");}
            }         
            
           
                }
                else{
                    System.out.println("server> Authentication failed");
                    System.exit(0);
                }
                
                in.close();
                out.close();
                requestSocket.close();
            } catch (Exception ex) {
            }
        }
            
    }

 }
