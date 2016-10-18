//2 omadiki
//icsd08011-icsd08041

//bingo client


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.*;
import java.util.*;
import javax.swing.*;


public class bingo_client extends JFrame   implements ActionListener{

      boolean answer;
     String apantisi;
     
    String name ="//localhost:1099/game_server";
    
 JPanel row1 =new JPanel(); //dimiourgo nea grammi gia na valo to label kai to textfield 
 JLabel Username=new JLabel("Username",JLabel.RIGHT); //dimiourgo ena JLabel gia na gra4o to onoma 
 JTextField Username_ = new JTextField(15);//dimiourgo ena JTextField oste na mporo na gra4o 
     
 JPanel row2 =new JPanel(); //dimiourgo nea grammi gia na valo to label kai to textfield 
 JLabel Code=new JLabel("Pin",JLabel.RIGHT); //dimiourgo ena JLabel gia na gra4o to pin
 JTextField Code_ = new JTextField(15);//dimiourgo ena JTextField oste na mporo na gra4o 
    
 JPanel row3 =new JPanel();
 JButton OK = new JButton("OK");// dimiourgo ena koumpi oste otan to patisei na eisagounte ta dedomena
 JButton cancel = new JButton("Cancel");//dimiourgo ena koumpi exodo 
    
     public bingo_client() throws IOException, ClassNotFoundException{
   
         super("Validation");
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//gia na klino to parathiro
    setVisible(true);// emfanizo to parathiro
    Container pane= getContentPane();// dimiourgo ena antikeimeno gia na valo ta pedia sto parathiro 
    GridLayout layout =new GridLayout(3,1);// dimourgo ena layout kai to vazo na emfanizete se 3 grammes kai 1 stili
    pane.setLayout(layout);//vazo ta lyouts sto pane 
    
    
    
    FlowLayout layout1=new FlowLayout();// dimiourgo ena Flowlayout gia tin proti grammi
    row1.setLayout(layout1);//oti vazo me row1 mpenei sto layout1 
    row1.add(Username);// vazo to lebel gia to onoma 
    row1.add(Username_);// vazo to textfield pou dino to onoma 
    pane.add(row1);//vazo ro row1 sto pane
     FlowLayout layout2=new FlowLayout();// dimiourgo ena Flowlayout gia tin proti grammi
    row2.setLayout(layout2);//oti vazo me row1 mpenei sto layout1 
    row2.add(Code);// vazo to lebel gia to pin 
    row2.add(Code_);// vazo to textfield pou dino to pin
    pane.add(row2);//vazo ro row1 sto pane
    
    
    
    FlowLayout layout3=new FlowLayout();
    row3.setLayout(layout3);
    row3.add(OK);//vazo to OK 
    row3.add(cancel);//vazo to cancel
    pane.add(row3);
    
    setContentPane(pane);
    pack();
    OK.addActionListener(this);//otan patithi to OK enegropoihte to actionlistener
    cancel.addActionListener(this);//otan patithi to Cancel enegropoihte to actionlistener
  }
    
     
     public void actionPerformed(ActionEvent evet) {//ektelesi actionlistener
        Object source= evet.getSource();// dimiourgo ena antikeimeno etsi oste na paro to onoma tou gegonotos
         
        if(source==OK){
        
        try {
            diepafi look_op =(diepafi)Naming.lookup(name);// dinoume tin dieufinsi gia epikinonia me ton server
            answer=look_op.authentication(Username_.getText(),Code_.getText()); // ekteloume tin methodo gia validate 
            
         diepafi_2 look_op1 =(diepafi_2)Naming.lookup(name);// dinoume tin dieufinsi gia epikinonia me ton server
            
                if(answer==true){// an einai or8o tote 
               setVisible(false);// svino to proigoumeno para8iro
               client_choice choice =new client_choice(look_op,look_op1);// ektelo to menu 
                    
                }
                else{
                JOptionPane.showMessageDialog(null,"Lathos kodikos prosvasis","Error",JOptionPane.PLAIN_MESSAGE);
               bingo_client a= new bingo_client();
                }
 
            }
            catch (Exception e) {
            System.out.println("provlima me to Operations");
            System.exit(0);
                     }
        }  
        
     if(source==cancel){
         JOptionPane.showMessageDialog(null,"Exodos apo to systyma ","Error",JOptionPane.PLAIN_MESSAGE);
         System.exit(0);
     
     }   

    }  
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
      
        
   bingo_client a= new bingo_client();  
        
        
    }
}
