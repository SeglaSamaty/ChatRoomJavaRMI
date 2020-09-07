//package groupChatServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServeur extends Remote{
	public int join(String nickname, ISalonCallback callback) throws RemoteException;
	public String [] chatters() throws RemoteException;
	public int postMessage(int ident, String msg) throws RemoteException;
	public int leave(int ident) throws RemoteException;
}
