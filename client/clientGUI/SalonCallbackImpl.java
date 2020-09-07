

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SalonCallbackImpl extends UnicastRemoteObject implements ISalonCallback {

	/**
	 * 
	 */
	String nickname;
	IServeur serveur;
	
	private static final long serialVersionUID = 1L;

	protected SalonCallbackImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notify(int code, String nickname, String msg) throws RemoteException {
		// TODO Auto-generated method stub
	
		System.out.println(code+"  "+nickname+"  "+msg);
		
		Platform.runLater(
				  () -> {
					    Scene mainSc = Client.getMainChatScene();
						VBox chatP = (VBox) mainSc.lookup("#chatPane");
						//chatP.setSpacing(1);
						
						Label text = new Label();
						if (code == 0) {
							text.setText("\n "+nickname+" :: "+msg);
							
							text.setTextFill(Color.web("#4d78ce"));
							text.setFont(new Font("Arial",12));
							if (Client.getNickname().equals(nickname)) {
								text.setTextFill(Color.web("#5ea17d"));
							}
						}
						
						if (code == 1) {
							text.setText("\n "+nickname+" "+"a rejoint la discusion");
							text.setTextFill(Color.web("#ef1414"));
							text.setFont(new Font("Arial",12));
							if (Client.getNickname().equals(nickname)) {
								text.setText("\n vous avez rejoint la discusion ");
								text.setTextFill(Color.web("#ef1414"));
							}
							try {
								String[] listU = Client.serveur.chatters();
								String listUasString = ""; 
								for (int i = 0; i < listU.length; i++) {
									listUasString = listUasString+listU[i]+'\n';
								}
								((Label) mainSc.lookup("#userlist")).setText(listUasString);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						
						if (code == 2) {
							text.setText("\n "+ nickname+" a quitter la discusion ");
							text.setTextFill(Color.web("#ef1414"));
							text.setFont(new Font("Arial",12));
							if (Client.getNickname().equals(nickname)) {
								text.setText("\n Vous avez quitter la discusion ");
								text.setTextFill(Color.web("#ef1414"));
							}
							try {
								String[] listU = Client.serveur.chatters();
								String listUasString = ""; 
								for (int i = 0; i < listU.length; i++) {
									listUasString = listUasString+listU[i]+'\n';
								}
								((Label) mainSc.lookup("#userlist")).setText(listUasString);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
							
						chatP.getChildren().add(text);

				  }
				);
		
	} 
	
}