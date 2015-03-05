package main.java.pw.bitcoinroulette.client;
	
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.pw.bitcoinroulette.client.game.GameCtrl;
import main.java.pw.bitcoinroulette.client.lobby.LobbyCtrl;
import main.java.pw.bitcoinroulette.client.login.LoginCtrl;
import main.java.pw.bitcoinroulette.library.AuthPlayer;
import main.java.pw.bitcoinroulette.library.Lobby;
import main.java.pw.bitcoinroulette.library.RouletteServer;
import main.java.pw.bitcoinroulette.library.ServerGame;


public class Main extends Application {
	public Scene loginScene;
	public Scene lobbyScene;
	public Scene gameScene;
	public Scene accountScene;
	public Stage stage;
	public RouletteServer rouletteServer;
	public AuthPlayer authPlayer;
	public ServerGame serverGame;
	
	public Main(){
		try {
			Registry registry = LocateRegistry.getRegistry();
			this.rouletteServer = (RouletteServer)registry.lookup("RouletteServer");
		} catch(RemoteException | NotBoundException e){
			e.printStackTrace();
		}
	}
	
	public void start(Stage stage) throws Exception {
		
		
		this.stage = stage;
		stage.setResizable(false);
		
		/* Login Screen */
		FXMLLoader loginFxml = new FXMLLoader(getClass().getResource("login/Login.fxml"));
		loginFxml.setController(new LoginCtrl(this));
		loginScene = new Scene(loginFxml.load());
		
		
		/* Game Screen */
		FXMLLoader gameFxml = new FXMLLoader(getClass().getResource("game/Game.fxml"));
		gameFxml.setController(new GameCtrl(this));
		gameScene = new Scene(gameFxml.load());
		
//		/* Account Screen */
//	    FXMLLoader accountFxml = new FXMLLoader(getClass().getResource("account/Account.fxml"));
//	    accountFxml.setController(new AccountCtrl(this));
//	    accountScene = new Scene(accountFxml.load());
	    
	    stage.setWidth(1024);
		stage.setHeight(700);
	    stage.setTitle("Bitcoin Roulette");
	    stage.setScene(loginScene);
	    stage.show();
	}
	
	public void loadLobby(Lobby lobby) throws IOException{
		FXMLLoader lobbyFxml = new FXMLLoader(getClass().getResource("lobby/Lobby.fxml"));
		lobbyFxml.setController(new LobbyCtrl(this, lobby));
		lobbyScene = new Scene(lobbyFxml.load());
		stage.setScene(lobbyScene);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}