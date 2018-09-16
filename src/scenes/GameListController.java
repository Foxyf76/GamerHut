package scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import store.Game;
import store.User;

public class GameListController implements Initializable {

	@FXML
	public TableView table;
	@FXML
	public TableColumn name;
	@FXML
	public TableColumn year;
	@FXML
	public TableColumn platform;
	@FXML
	public TableColumn genre;
	@FXML
	public TableColumn rating;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadGames();
	}
	
	public void profileScene(ActionEvent event) throws Exception {
		SceneCreator.launchScene("/scenes/profile.fxml");
	}

	public void loadGames() {

		User user = Main.getCurrentUser();

		final ObservableList<Game> data = FXCollections.observableArrayList(user.getGames());

		name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
		year.setCellValueFactory(new PropertyValueFactory<Game, Integer>("year"));
		platform.setCellValueFactory(new PropertyValueFactory<Game, String>("platform"));
		genre.setCellValueFactory(new PropertyValueFactory<Game, String>("genre"));
		rating.setCellValueFactory(new PropertyValueFactory<Game, String>("rating"));

		table.setItems(data);
	}
}
