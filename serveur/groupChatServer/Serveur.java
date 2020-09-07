//package groupChatServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Serveur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("java.security.policy","file://../serveur.policy");
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			IServeur remoteServeurObj = (IServeur) new ServeurImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("serveurgroupchat", remoteServeurObj);
			System.out.println("Group chat Server is running ...");
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
