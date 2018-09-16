package scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import store.Game;
import store.User;

public class GameViewController implements Initializable {

	@FXML
	public Label name;
	@FXML
	public Label year;
	@FXML
	public Label genre;
	@FXML
	public Label platform;
	@FXML
	public Label description;
	@FXML
	public Label rating;
	@FXML
	public ImageView imageView;

	@FXML
	public ImageView metacritic;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDetails();
	}

	public void profileScene(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/profile.fxml");
	}

	public void setDetails() {

		Game game = Main.getCurrentGame();
		name.setText(game.getName());
		year.setText(String.valueOf(game.getYear()));
		genre.setText(game.getGenre());
		platform.setText(game.getPlatform());
		description.setText(game.getDescription());
		rating.setText(String.valueOf(game.getRating()));
		Image image = new Image("file:" + game.getImage());
		imageView.setImage(image);

	}

	public void launchMetacritic() throws IOException {
		Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome https://www.google.ie/search?q="
				+ Main.getCurrentGame().name
				+ "&oq=assassins+creed+metacritic&aqs=chrome..69i57j69i60l3j0l2.3725j0j4&sourceid=chrome&ie=UTF-8" });
	}

}
