package sample;

/**
 * @author Valentyn Kofanov (https://github.com/avmepy)
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setControllerFactory(t -> new Controller(new Model()));

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}