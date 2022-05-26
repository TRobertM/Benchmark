package controllers;

import benchmark.Benchmark;
import exceptions.OptionsNotSelectedException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML
    Label minimizeButton;
    @FXML
    Text errorText;


    private final String[] functions = {null, "Geometric", "Gauss"};
    private final Integer[] digits = {null, 25000, 50000, 100000, 200000, 400000};
    protected Benchmark benchmark;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        functionSelector.getItems().addAll(functions);
        numberSelector.getItems().addAll(digits);
    }

    public void stop(){
        myPane2.setVisible(false);
        myPane.setVisible(true);
        benchmark.stop();
    }

    public void exit(){
        exitButton.setOnAction((ActionEvent event) -> Platform.exit());
        System.exit(0);
    }

    class calculatePI extends Task<Void> {
        @Override
        protected Void call(){
            myPane2.setVisible(true);
            myPane.setVisible(false);
            benchmark.warmUp();
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

    public void start() throws OptionsNotSelectedException {
        benchmark = new Benchmark();
        if(functionSelector.getValue() == null || numberSelector.getValue() == null){
            errorText.setText("Please select options before starting");
            errorText.setFill(Color.RED);
            throw new OptionsNotSelectedException();
        }
        errorText.setText("*Gauss formula may take a long time for longer number of digits");
        errorText.setFill(Color.BLACK);
        calculatePI task = new calculatePI();
        String function = functionSelector.getValue();
        int numberOfDigits = numberSelector.getValue();
        benchmark.initialize(numberOfDigits, function);
//        int cores =  Runtime.getRuntime().availableProcessors();
//        for(int i = 0 ; i < cores; i++) {
            new Thread(task).start();
//        }
    }

    public void goBack(){
        myPane.setVisible(true);
        endPane.setVisible(false);
    }

    public void minimize(){
        Stage stage = (Stage)minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
