package scenes;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import store.Game;
import store.User;
import sun.java2d.cmm.Profile;

public class CreateGameController implements Initializable {

	File gameFile;
	CreateAccountController createAccount;
	ProfileController profile;

	@FXML
	public TextField name;
	@FXML
	public TextField year;
	@FXML
	public TextArea description;
	@FXML
	public Slider rating;
	@FXML
	public ComboBox<String> genre;
	@FXML
	public ComboBox<String> platform;
	@FXML
	public ImageView gameImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		genre.getItems().addAll("Shooter", "RPG", "Sports", "Adventure", "Puzzle");
		platform.getItems().addAll("PC", "PS4", "PS3", "PS2", "PS1", "PSP", "XBOX-ONE", "XBOX360", "XBOX", "N64", "WII",
				"WII-U", "NINTENDO DS", "NINTENDO SWITCH", "GAMEBOY", "GAMEBOY ADVANDE", "ARCADE");
	}

	
	public void profileScene(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/profile.fxml");
	}

	@FXML
	public String uploadImage() {
		User user = Main.getCurrentUser();
		String ID = user.getID();
		FileChooser chooser = new FileChooser();
		Label chosen = new Label();
		gameFile = chooser.showOpenDialog(Main.primaryStage);
		if (gameFile != null) {
			String fileAsString = gameFile.toString();
			Image image = new Image("file:" + fileAsString);
			gameImage.setImage(image);
			user.setImageURL(gameFile.toString());
		}
		return gameFile.toString();
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

	public void addGame() throws Exception {
		// public Game(String name, int year, String description, String platform,
		// String genre, double rating, String image) {
		int yearValue = Integer.parseInt(year.getText());
		Game game = new Game(getSaltString(), name.getText(), yearValue, description.getText(),
				platform.getSelectionModel().getSelectedItem().toString(), genre.getSelectionModel().getSelectedItem().toString(), rating.getValue(), gameFile.toString());
		User user = Main.getCurrentUser();
		String ID = user.getID();

		for (int i = 0; i < Main.getUserList().size(); i++) {
			if (ID.equals(Main.users.get(i).getID())) {
				Main.users.get(i).games.add(game);
				System.out.println("Added Game!");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText(null);
				alert.setContentText("Game Created!");
				alert.showAndWait();
				Main.saveUser();

			} else {
				System.out.println("ERROR");
			}
		}
	}

}
