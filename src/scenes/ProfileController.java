package scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import store.Game;
import store.User;

public class ProfileController implements Initializable {

	@FXML
	public ImageView profileImage;
	@FXML
	public Label username;
	@FXML
	public HBox gamesHBox;

	Main main;
	ActionEvent event;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDetails();
	}

	public void createGameScene(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/createGame.fxml");
	}

	public void gameListScene(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/gameList.fxml");
	}

	public void gameViewScene(ActionEvent event) throws Exception {
		User user = Main.getCurrentUser();
		System.out.println(user.getImageURL());
		if (Main.getCurrentUser().getGames().size() > 0) {
			SceneCreator.launchScene("/scenes/gameView.fxml");
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("NO GAMES");
			alert.setHeaderText(null);
			alert.setContentText("You have not added any games!");
			alert.showAndWait();
		}
	}
	
	public void clear() {
		profileImage.setImage(null);
		Image image = new Image("file:"+Main.getCurrentUser().getImageURL());
		profileImage.setImage(image);
		
	}

	public void setDetails() {

		// SET USERNAME
		profileImage.setImage(null);
		User user = Main.getCurrentUser();
		if (user.getImageURL().equals("null")) {
			Image image = new Image(ResourceLoader.load("./images/placeholder.png"));
			profileImage.setImage(image);
		} else {
			Image image = new Image("file:" + user.getImageURL());
			profileImage.setImage(image);
			System.out.println("DONE");
		}
		username.setText(user.getUsername());

		// SET GAMES LIST

		if (user.getGames() != null && user.getGames().size() != 0) {
			for (int i = 1; i < user.getGames().size(); i++) {
				System.out.println("SIZE " + user.getGames().size());
				String gameImage = user.getGames().get(i).getImage();
				String gameID = user.getGames().get(i).getID();

				Image image = new Image("file:" + gameImage);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(150);
				imageView.setFitWidth(100);
				imageView.setId(gameID);

				gamesHBox.setSpacing(10);
				gamesHBox.getChildren().add(imageView);

				imageView.setOnMouseClicked((MouseEvent e) -> {

					Game game = getGameByID(imageView.getId());
					Main.setCurrentGame(game);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Selected");
					alert.setHeaderText(null);
					alert.setContentText("You have selected; " + game.getName() + "\n Click 'Open' to proceed!");
					alert.showAndWait();

				});
			}
		}
	}
	

	public void uploadImage() throws Exception {
		User user = Main.getCurrentUser();
		String username = user.getUsername();
		FileChooser chooser = new FileChooser();
		Label chosen = new Label();
		File file = chooser.showOpenDialog(Main.primaryStage);
		if (file != null) {
			String fileAsString = file.toString();
			Image image = new Image("file:" + fileAsString);
			profileImage.setImage(image);
			user.setImageURL(file.toString());
			for (int i = 0; i < Main.getUserList().size(); i++) {
				if (username.equals(Main.users.get(i).getUsername())) {
					Main.users.get(i).setImageURL(fileAsString);
				}
			}
		}
	}

	public static Game getGameByID(String ID) {
		User user = Main.getCurrentUser();
		ArrayList<Game> gameAllGames = Main.getGameList(user);
		Game game = null;
		for (int i = 0; i < gameAllGames.size(); i++) {
			System.out.println("Inside loop");
			if (gameAllGames.get(i).getID().equals(ID)) {
				game = gameAllGames.get(i);
				System.out.println("FOUND GAME WITH ID");
			} else {
				System.out.println("NOT FOUND GAME WIT ID");
			}
		}
		return game;
	}

	@FXML
	public void logOut(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/landing.fxml");
		Main.saveUser();
	}
}
