import java.rmi.Remote;
import java.rmi.RemoteException;

public interface diepafi extends Remote{
    
    public boolean authentication(String username, String pin) throws RemoteException;
       public Card[] deltia(long seed) throws RemoteException;
          public   BingoBall  announceNextBalls() throws RemoteException;
       public boolean verify(Card c)throws RemoteException;
    public void close() throws RemoteException;
    
}
