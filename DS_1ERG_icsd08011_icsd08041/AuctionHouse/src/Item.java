


import java.util.*;

public class Item{
    
     String name;
   
     float timi_ekk;
     float timi_current;
   float x;
     
public Item(String name,float timi_ekk)
    {
        this.name=name;
       
        this.timi_ekk=timi_ekk;
        
        
    }
    
    public String getName(){return name;}
   
    public float getTimi_ekk(){return timi_ekk;}
   
    
    
    //eisagwgi prosforas
    
    public void prosfora( ) {
     System.out.println("dwste tin prosfora");
      Scanner p=new Scanner (System.in);
	String prosfora = (String) p.nextLine();
      float x = Float.parseFloat(prosfora);
System.out.println("i prosfora tou client einai"+x);
   timi_current = timi_ekk + x;
System.out.println("to neo ypoloipo sas einai"+timi_current);

    }
    
    
    
    public void timi_current() {
          //timi_current = timi_ekk + x;
System.out.println("to neo ypoloipo sas einai"+timi_current);
    }
    
    
    public String toString(){
       
  return "\n stoixeia antikeimenou se plistiriasmo"+"\n onoma: "+name+"\n ID: "
          +"\n timi ekkinisis: "+timi_ekk+"\n trexous timi : "+timi_current;
       }
    
}
