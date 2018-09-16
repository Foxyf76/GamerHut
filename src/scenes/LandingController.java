package scenes;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import store.User;

public class LandingController {
	
	ProfileController profile;

	@FXML
	public TextField username;
	@FXML
	public PasswordField password;
	ArrayList<User> objects = Main.getUserList();

	public void createAccountScene(ActionEvent event) throws IOException {
		SceneCreator.launchScene("/scenes/createAccount.fxml");
		User user = null;
		String usernameText = username.getText();
		String passwordText = password.getText();

		for (int i = 0; i < objects.size(); i++) {
			System.out.println("INSIDE");
			if (usernameText.equals(objects.get(i).getUsername())
					&& passwordText.equals(objects.get(i).getPassword())) {
				user = (objects.get(i));
				System.out.println("Found user!");
			} else {
				System.out.println("Error, User not found!");
			}
		}
	}

	@FXML
	public void profileScene(ActionEvent event) throws IOException {

		User user = null;
		String usernameText = username.getText();
		String passwordText = password.getText();

		for (int i = 0; i < objects.size(); i++) {
			if (usernameText.equals(objects.get(i).getUsername())
					&& passwordText.equals(objects.get(i).getPassword())) {
				user = (objects.get(i));
				System.out.println("Found user!");
				Main.setCurrentUser(user);
				//profile.setDetails(Main.getCurrentUser());
				//System.out.println(Main.currentUser.toString());
				SceneCreator.launchScene("/scenes/profile.fxml");
			} else {
				System.out.println("Error, User not found!");
			}
		}
	}
	
	public void getUsers() {
		System.out.println(Main.getUserList());
	}


}
