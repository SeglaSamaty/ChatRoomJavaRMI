//package groupChatServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServeurImpl extends UnicastRemoteObject implements IServeur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Object[]> listUsers = new ArrayList<Object[]>();
	int lastId = 0;
	

	protected ServeurImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int join(String nickname, ISalonCallback callback) throws RemoteException {
		// TODO Auto-generated method stub
		if (listUsers.size() == 0) {
			lastId++;
			Object[] user = { ((Integer) lastId), nickname, callback };
			listUsers.add(user);
			callback.notify(1, ((String) nickname), "New user");
		}else {
			boolean nicknameExist = false;
			for (int i = 0; i < listUsers.size(); i++) {
				Object[] user = listUsers.get(i);
				if (nickname.equals(((String) user[1]))) {
					//callback.notify(-29, ((String) nickname), "New user Failed");
					nicknameExist = true;
					return -1;
					//break;
				}	
			}
			if (!nicknameExist) {
				lastId++;
				Object[] user = { ((Integer) lastId), nickname, callback };
				listUsers.add(user);
				for (int j = 0; j < listUsers.size(); j++) {
					Object[] u = listUsers.get(j);
					((ISalonCallback)u[2]).notify(1, ((String) nickname), "New user");
				}
			}
		}
		
		
		
		//callback.test();//testing smthg ... line to be dropped later
		return lastId;
	}

	@Override
	public String [] chatters() throws RemoteException {
		// TODO Auto-generated method stub
		String  listOfAllNicknames [] = new String [listUsers.size()];
		for (int i = 0; i < listUsers.size(); i++) {
			Object[] user = listUsers.get(i);
			listOfAllNicknames[i] = (String) user[1];
			//listOfAllNicknames = listOfAllNicknames +((String) user[1])+ "\n";
		}
		return listOfAllNicknames;
	}

	@Override
	public int postMessage(int ident, String msg) throws RemoteException {
		// TODO Auto-generated method stub
		int toRet = -1;
		for (int i = 0; i < listUsers.size(); i++) {
			Object[] user = listUsers.get(i);
			if (ident == ((Integer) user[0]).intValue()) {
				
				for (int j = 0; j < listUsers.size(); j++) {
					Object[] u = listUsers.get(j);
					((ISalonCallback)u[2]).notify(0, ((String) user[1]), msg);
				}
				toRet = 0;
				break;
			}	
		}
		return toRet;
	}

	@Override
	public int leave(int ident) throws RemoteException {
		// TODO Auto-generated method stub
		int toRet = -1;
		String nickname = "";
		for (int i = 0; i < listUsers.size(); i++) {
			Object[] user = listUsers.get(i);
			if (ident == ((Integer) user[0]).intValue()) {
				nickname = (String) listUsers.get(i)[1];
				listUsers.remove(i);
				toRet = 0;
				break;
			}	
		}
		for (int i = 0; i < listUsers.size(); i++) {
			Object[] user = listUsers.get(i);
			((ISalonCallback)user[2]).notify(2, nickname, "User left");
		}
		return toRet;
	}

}
