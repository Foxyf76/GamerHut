package scenes;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneCreator {
	
    // launching the new scene based on the .fxml file name passed in the argument as a String variable
    // building the scene and setting the value for the instance variable loader
    public static void launchScene (String sceneName) throws IOException {

        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(sceneName));
        Main.setRoot(loader.load());
        Scene scene = new Scene(Main.getRoot());
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
}