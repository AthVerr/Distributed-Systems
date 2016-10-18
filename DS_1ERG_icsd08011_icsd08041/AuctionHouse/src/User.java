
public class User{
    private String username, password;
    float poso;
    //private boolean authenticated;
    int ID=1;
    
    
    public User(String username, String password) {
       this.username = username;
       this.password = password;
       this.ID=ID;
       //this.poso=0;
       //this.authenticated = false;
       ID++;
    }
    
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public int getID(){return ID;}
    public float getPoso(){return poso;}
    //public boolean getAuthenticated(){return authenticated;}

    //public void setAuthenticated(boolean authenticated)
    //{
    //    this.authenticated = authenticated;
    //}
}


