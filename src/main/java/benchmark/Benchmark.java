package benchmark;

import controllers.MainSceneController;
import exceptions.MethodNotImplementedException;
import formulas.Gauss;
import formulas.Geometric;
import formulas.PI;
import formulas.Score;
import javafx.application.Platform;
import logger.FileLogger;
import timer.Timer;

import java.math.BigDecimal;

public class Benchmark implements IBenchmark, Runnable{
    protected int numberOfDigits;
    protected String formula;
    protected Timer myTimer;
    protected PI piMethod;
    public double finish;

    @Override
    public void initialize(Object ... params) {
        numberOfDigits = (int)params[0];
        formula = (String)params[1];
        myTimer = new Timer();
        if(formula.equals("Geometric")){
            piMethod = new Geometric();
        } else piMethod = new Gauss();
    }

    @Override
    public void run(){
        myTimer.start();
        piMethod.calculatePi(numberOfDigits);
        finish = myTimer.stop();
        System.out.println(finish/1000000000.0);
    }

    @Override
    public void run(Object ... params) throws MethodNotImplementedException{
        throw new MethodNotImplementedException("run(Object ... params) not implemented");
    }

    @Override
    public void stop() {
        piMethod.stop();
    }

    @Override
    public void warmUp() {
        piMethod.calculatePi(1000);
    }
}
