//2 omadiki
//icsd08011-icsd08041

public class Winner_user {
    // dilwsi idiotitwn 
    private String username;
    private String Winner;
    private int ID;
   
    // dimiourgia constructor me tis 2 idiotites
    public Winner_user(int Id,String username,String winner){
        this.ID=Id;
        this.username= username;
        this.Winner= winner;
        }
    
    // methodoi gia tin apodosi kia epistrofi timwn twn idiotitwn
    public void set_username(String u){username=u;}
    public String get_username(){return username;}
  
    public void set_winner(String w){Winner=w;}
    public String get_winner(){return Winner;}
    
    public int get_ID(){return ID;}
  
    public String toString(){
    
        return "Username: "+this.username+"\nWinner: "+this.Winner+ "\nID: "+this.ID;
    }

}
