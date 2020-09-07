

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{

	public static String nickname;
	public static Scanner inputSc;
	public static ISalonCallback client;
	public static String serviceName = "serveurgroupchat";
	public static String serveurAddr = "192.168.1.108";
	public static IServeur serveur;
	public static int ident = 99;
	static Stage primStage;
	static Scene loginScene;
	static Scene mainChatScene;
	
	public static void main(String[] args) {
		//System.out.println("Gentil code....");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		
		 Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
		 setLoginScene(new Scene(login));
		 
		 Parent mainChat =  FXMLLoader.load(getClass().getResource("mainScene.fxml"));
		 setMainChatScene(new Scene(mainChat));
		 
		 
		 primaryStage.setScene(getLoginScene());
		 
		 setPrimStage(primaryStage);
		 primaryStage.show();
		
	}

	public static String getNickname() {
		return nickname;
	}

	public static void setNickname(String nickname) {
		Client.nickname = nickname;
	}

	public static ISalonCallback getClient() {
		return client;
	}

	public static void setClient(ISalonCallback client) {
		Client.client = client;
	}

	public static IServeur getServeur() {
		return serveur;
	}

	public static void setServeur(IServeur serveur) {
		Client.serveur = serveur;
	}

	public static int getIdent() {
		return ident;
	}

	public static void setIdent(int ident) {
		Client.ident = ident;
	}

	public static String getServiceName() {
		return serviceName;
	}

	public static void setServiceName(String serviceName) {
		Client.serviceName = serviceName;
	}

	public static String getServeurAddr() {
		return serveurAddr;
	}

	public static void setServeurAddr(String serveurAddr) {
		Client.serveurAddr = serveurAddr;
	}

	public Scene getLoginScene() {
		return loginScene;
	}

	public static void setLoginScene(Scene lloginScene) {
		loginScene = lloginScene;
	}

	public static Scene getMainChatScene() {
		return mainChatScene;
	}

	public static void setMainChatScene(Scene mmainChatScene) {
		mainChatScene = mmainChatScene;
	}

	public static Stage getPrimStage() {
		return primStage;
	}

	public static void setPrimStage(Stage pprimStage) {
		primStage = pprimStage;
	}
	
	

}
