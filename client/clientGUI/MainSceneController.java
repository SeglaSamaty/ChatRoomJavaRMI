

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MainSceneController {
	@FXML
	public Label userlist;
	
	@FXML
	public Label nickname;
	
	@FXML
	public TextArea msg;
	
	@FXML
	public Button leave;
	
	@FXML
	public Button send;
	
	@FXML
	public ScrollPane scrollPaneChat;
	
	@FXML
	public VBox chatPane;
	
	final FlowPane container = new FlowPane();

	public void handleSendButtonClick() {
			String message = msg.getText();
			if (!message.isEmpty()) {
				try {
					Client.getServeur().postMessage(Client.getIdent(), message);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(msg);
			}
			msg.setText("");
			
	
	}
	
	public void handleLeaveButtonClick() {
		
		try {
			Client.getServeur().leave(Client.getIdent());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System. exit(0);
		System.out.println("You left the chat room");

	}
	
	@FXML
	public void initialize() {
		//chatPane.setSpacing(3);
		
		userlist.setText(Client.nickname);
		
		//chatPane.getChildren().add(scrollPaneChat);
		//container.getChildren().add(chatPane);
		//scrollPaneChat.setContent(chatPane);
		//scrollPaneChat.setPannable(true);
	}
	
}
