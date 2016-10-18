//2 omadiki
//icsd08011-icsd08041

import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class client_choice {    
        
    public  client_choice(diepafi look_op, diepafi_2 look_op1) throws IOException, ClassNotFoundException{
                
   String epilogi="";
   //orizw tis epiloges p exei o xristis
   String[] choices = {"Give play card","new number","win option","already winners","Exit the game"};
   Card[]    karta;
   String  win;
   boolean     answer;
   BingoBall ball;    
   BingoBall b1=new  BingoBall(75);
   Card c1=new Card();          
   epilogi=(String)JOptionPane.showInputDialog(null, "Give your choice", "choice", JOptionPane.QUESTION_MESSAGE,null, choices,"");

 if(epilogi=="Give play card"){
     karta = look_op.deltia(6); //syndeete me server kai epistrefei tis kartes stous paixtes
  }

  if(epilogi=="new number"){
    look_op.announcedBalls(b1); //syndeete me server kai epistrefei ta tyxaia noumera
  }

     if(epilogi=="win option"){ //epilegei o xristis oti ekane bingo
  answer=look_op.verify(c1); //sindesi me server
 }

         if(epilogi=="already winners"){  //sindesi me win_server
   win= look_op1.Current_Nikitis("anna"); //dinei tous kataxwrimenous nikites
 }
                   
   if(epilogi=="Exit the game"){
 JOptionPane.showMessageDialog(null,"Exit","exit from server",JOptionPane.PLAIN_MESSAGE);
 look_op.close(); 
 System.exit(0);//termatismos tou programmatos kai para8irou
                              
 }    
        
        
    }
    
}
