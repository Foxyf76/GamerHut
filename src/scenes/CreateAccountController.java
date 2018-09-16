package scenes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import store.Game;
import store.User;

public class CreateAccountController {

	@FXML
	private TextField fullname;
	@FXML
	private DatePicker date;
	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField password2;
	@FXML
	public void landingScene(ActionEvent event) throws IOException {
		SceneCreator.launchScene("/scenes/landing.fxml");
	}

	public ArrayList<User> getObjects() {
		ArrayList<User> getUsers = new ArrayList();
		for (int i = 0; i < Main.users.size(); i++) {
			getUsers.add(Main.users.get(i));
			System.out.println(Main.users.get(i).toString());
		}
		return getUsers;
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public void addUsers() throws Exception {
		if (fullname.getText().equals("") || date.toString().equals("") || username.getText().equals("")
				|| email.getText().equals("") || password.getText().equals("") || password2.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Input Error");
			alert.setContentText("Please fill out all fields!");
			alert.showAndWait();
		} else {
			String imageURL = ("null");
			ArrayList<Game> games = new ArrayList();
			if (password.getText().equals(password2.getText())) {
				User user = new User(getSaltString(), fullname.getText(), date.toString(), username.getText(),
						email.getText(), password.getText(), imageURL, games);
				Main.users.add(user);
				System.out.println("Created User!");
				Main.saveUser();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Input Error");
				alert.setContentText("Please make sure passwords match!");
				alert.showAndWait();
			}
		}
	}
}
