package controllers;

import benchmark.Benchmark;
import javafx.application.Platform;
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

    private String[] functions = {"Geometric", "Gauss"};
    private Integer[] digits = {10000, 25000, 50000, 100000};
    private String function;
    private int numberOfDigits;
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
        exitButton.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
    }

    public void start(){
        benchmark = new Benchmark();
        function = functionSelector.getValue();
        numberOfDigits = numberSelector.getValue();
        benchmark.initialize(numberOfDigits, function);
        benchmark.warmUp();
        new Thread(benchmark).start();
        System.out.println(Thread.currentThread().getName());
    }

}
