package controllers;

import benchmark.Benchmark;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    private ChoiceBox<String> functionSelector;
    @FXML
    private ChoiceBox<Integer> numberSelector;
    @FXML
    private Text result;
    @FXML
    private Button exitButton;
    @FXML
    private Pane myPane2;
    @FXML
    private Pane myPane;
    @FXML
    private Pane endPane;
    @FXML
    private Text finishTime;

    private final String[] functions = {"Geometric", "Gauss"};
    private final Integer[] digits = {25000, 50000, 100000, 200000, 400000};
    protected Benchmark benchmark;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        functionSelector.getItems().addAll(functions);
        numberSelector.getItems().addAll(digits);
    }

    public void stop(){
        myPane2.setVisible(false);
        benchmark.stop();
    }

    public void exit(){
        exitButton.setOnAction((ActionEvent event) -> Platform.exit());
    }

    class calculatePI extends Task<Void> {
        @Override
        protected Void call(){
            myPane2.setVisible(true);
            myPane.setVisible(false);
            result.setText("Running");
            benchmark.run();
            if(benchmark.check() == true){
                myPane2.setVisible(false);
                myPane.setVisible(true);
            } else {
                myPane2.setVisible(false);
                endPane.setVisible(true);
                result.setText("Score: " + String.valueOf(benchmark.getResult()));
                finishTime.setText(benchmark.getTime() + " seconds");
            }
            return null;
        }
    }

    public void start() {
        benchmark = new Benchmark();
        calculatePI task = new calculatePI();
        String function = functionSelector.getValue();
        int numberOfDigits = numberSelector.getValue();
        benchmark.initialize(numberOfDigits, function);
        benchmark.warmUp();
        new Thread(task).start();
    }

    public void goBack(){
        myPane.setVisible(true);
        endPane.setVisible(false);
    }
}
