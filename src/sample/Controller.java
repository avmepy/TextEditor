package sample;

/**
 * @author Valentyn Kofanov (https://github.com/avmepy)
 */

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private TextArea areaText;

    @FXML
    private TextField forSearch;

    @FXML
    private Label forCount;

    @FXML
    private Button buttonForSearch;

    private java.nio.file.Path path;


    final HashSet<Character> vowels = new HashSet<Character>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));



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

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("*.txt", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

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
    private void onCountVowelAndSpec() {
        String data = areaText.getText();
        int n = data.length();
        int vowel = 0;
        int spec = 0;
        for (int i = 0; i < n; ++i){
            char cur = data.charAt(i);
            if (Character.isDigit(cur)){
                continue;
            }
            if (Character.isLetter(cur)){
                if (vowels.contains(cur)){
                    vowel++;
                }
                continue;
            }
            spec++;
        }

        forCount.setText("Vowels: " + Integer.toString(vowel) + "\nSpecial: " + Integer.toString(spec));
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Simple TextEditor (Valentino)");
        alert.show();
    }


    @FXML
    private void onRegexSearch(){
        String data = areaText.getText();
        String res = "\n ========= regex search result =========\n";
        Matcher matcher = Pattern.compile(forSearch.getText()).matcher(data);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            res = res + data.substring(start,end);
        }

        res = res + "\n =========/ regex search result /=========";
        areaText.setText(res);
    }

    public void onBar() {
        String text = areaText.getText();

        HashMap<Character, Integer> symb = new HashMap<Character, Integer>();
        for (int i = 0; i < text.length(); ++i){

            Character cur = text.charAt(i);

            if (symb.containsKey(cur)){
                symb.put(cur, symb.get(cur) + 1);
            }
            else{
                symb.put(cur, 1);
            }

        }

        System.out.println(symb);



        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("symbols");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("number");

        // Create a BarChart
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        // Series 1 - Data of 2014
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();
        dataSeries.setName("symbols");

        for (Map.Entry<Character, Integer> pair: symb.entrySet())
        {

            dataSeries.getData().add(new XYChart.Data<String, Number>(pair.getKey().toString(), pair.getValue()));
        }

        barChart.getData().add(dataSeries);

        barChart.setTitle("frequency histogram");

        VBox vbox = new VBox(barChart);

        Stage primaryStage = new Stage();

        primaryStage.setTitle("BarChart");
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);

        primaryStage.show();

    }
}
