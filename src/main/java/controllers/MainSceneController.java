package controllers;

import benchmark.Benchmark;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
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

    private final String[] functions = {"Geometric", "Gauss"};
    private final Integer[] digits = {10000, 25000, 50000, 100000};
    private Benchmark benchmark;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        functionSelector.getItems().addAll(functions);
        numberSelector.getItems().addAll(digits);
    }

    public void stop(){
        benchmark.stop();
    }

    public void exit(){
        exitButton.setOnAction((ActionEvent event) -> Platform.exit());
    }

    public void start(){
        benchmark = new Benchmark();
        calculatePI task = new calculatePI();
        String function = functionSelector.getValue();
        int numberOfDigits = numberSelector.getValue();
        benchmark.initialize(numberOfDigits, function);
        benchmark.warmUp();
        new Thread(task).start();
    }

    class calculatePI extends Task<Void> {
        @Override
        protected Void call(){
            benchmark.run();
            result.setText(String.valueOf(benchmark.getResult()));
            return null;
        }
    }
}
