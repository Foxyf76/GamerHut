package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import store.Game;
import store.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	public static Parent root;
	public static Stage primaryStage;
	public static User currentUser;
	public static Game currentGame;

	public static ArrayList<User> users = new ArrayList<User>();

	User user;

	public static Parent getRoot() {

		return root;
	}

	public static void setRoot(Parent root) {

		Main.root = root;
	}

	public static Stage getStage() {

		return primaryStage;
	}

	static void setStage(Stage stage) {

		Main.primaryStage = stage;
	}

	public static ArrayList<User> getUserList() {
		return users;
	}

	public static void setUserList(ArrayList<User> newUsers) {
		newUsers = users;
	}

	public static ArrayList<Game> getGameList(User user) {
		return user.getGames();
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static void setCurrentGame(Game game) {
		currentGame = game;
	}

	public static Game getCurrentGame() {
		return currentGame;
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			loadUsers();
			// setting up the login scene
			root = FXMLLoader.load(getClass().getResource("/scenes/landing.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Gamer Hut");
			Scene scene = new Scene(root, 900, 400);
			scene.getStylesheets().addAll(this.getClass().getResource("background.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			loadUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveUser() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
		out.writeObject(users);
		out.close();
	}

	@SuppressWarnings("unchecked")
	public static void loadUsers() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
		users = (ArrayList<User>) is.readObject();
		is.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
