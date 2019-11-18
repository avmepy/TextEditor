package sample;

/**
 * @author Valentyn Kofanov (https://github.com/avmepy)
 */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Controller {

    @FXML
    private TextArea areaText;

    @FXML
    private TextField forSearch;

    private java.nio.file.Path path;



    @FXML
    private void onOpen() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter(".docx", "*.docx");
        FileChooser.ExtensionFilter extFilter1 =
                new FileChooser.ExtensionFilter(".txt", "*.txt");
        fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            path = file.toPath();
            List<String> lines = Files.readAllLines(path);

            areaText.clear();
            areaText.setText(String.join("\n", lines));
        }
    }

    @FXML
    private void onSave() throws IOException{

        FileWriter fw = new FileWriter(path.toString());
        fw.write(areaText.getText());
        fw.close();

    }


    @FXML
    private void onSaveAs() throws IOException{
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            fw.write(areaText.getText());
            fw.close();
        }


    }

    @FXML
    private void onClose() {
        System.exit(0);
    }


    @FXML
    private void onCount() {

    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Simple TextEditor (Valentino)");
        alert.show();
    }

}
