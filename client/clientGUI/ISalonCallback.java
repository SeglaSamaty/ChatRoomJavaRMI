

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISalonCallback extends Remote{
	public void notify(int code, String nickname, String msg) throws RemoteException;
}