//2 omadiki
//icsd08011-icsd08041

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface diepafi_2 extends Remote{
    
    public String Katachorisi_Nikiti(String user_name) throws RemoteException;
    public String Current_Nikitis(String NIKITIS) throws RemoteException;
    
}
