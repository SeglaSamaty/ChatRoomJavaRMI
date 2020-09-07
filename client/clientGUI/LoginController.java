

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	public TextField nickname;
	
	@FXML
	public TextField serviceName;
	
	@FXML
	public TextField serveurAddr;
	
	@FXML
	public Label indications;
	
	
	public void handleLoginButtonClick() {
		Client.setNickname(nickname.getText());
		Client.setServeurAddr(serveurAddr.getText());
		Client.setServiceName(serviceName.getText());
		//System.out.println(Client.getNickname());
		
		////////////////////////////////////
		System.setProperty("java.security.Policy", "file:/test.policy");


		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		boolean go = true;

		try {
			Client.client = (ISalonCallback) new SalonCallbackImpl();
			Registry registry = LocateRegistry.getRegistry(Client.serveurAddr, 1099);
			Client.serveur = (IServeur) registry.lookup(Client.serviceName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			go = false;
			//e.printStackTrace();
		}

////////////////////////////////////

		if (go) {
			try {
				Client.setIdent(Client.serveur.join(Client.getNickname(), Client.client));
				//Client.setIdent(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Client.setIdent(-1);
				//e.printStackTrace();
			}
		}
		
		
		if(Client.getIdent() < 0) {
			//System.out.println("Connexion echouee "+Client.getNickname()+"!");
			indications.setText("Connexion echouee");
		}else {
			//move to the chat main screen
			System.out.println("Connexion reussi au nom de "+Client.getNickname()+"!");
			Scene mainSc = Client.getMainChatScene();
			((Label) mainSc.lookup("#nickname")).setText(Client.getNickname());
			try {
				//((Label) mainSc.lookup("#userlist")).setText(Client.serveur.chatters());
				String[] listU = Client.serveur.chatters();
				String listUasString = ""; 
				for (int i = 0; i < listU.length; i++) {
					listUasString = listUasString+listU[i]+'\n';
				}
				((Label) mainSc.lookup("#userlist")).setText(listUasString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			///////////////////////////////////////////////////
			// Save in config File
			
			File myObj = new File("config.txt");
			try {
				myObj.createNewFile();
				FileWriter myWriter = new FileWriter("config.txt");
				myWriter.write(Client.getNickname()+"\n"+Client.getServiceName()+"\n"+Client.getServeurAddr());
				myWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	//
			
			///////////////////////////////////////////////////
			
			Client.getPrimStage().setScene(mainSc);
			
		}
		
		
	}
	

	@FXML
	public void initialize() {
		try {
			BufferedReader buf = new BufferedReader( new FileReader( "config.txt" ));

			nickname.setText(buf.readLine());
			serviceName.setText(buf.readLine());
			serveurAddr.setText(buf.readLine());
			
			buf.close();
		}
		catch ( Exception e ) {			
			//e.printStackTrace();
		}
	}


}
