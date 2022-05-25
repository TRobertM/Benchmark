package benchmark;

import exceptions.MethodNotImplementedException;
import formulas.Gauss;
import formulas.Geometric;
import formulas.PI;
import timer.Timer;

public class Benchmark implements IBenchmark, Runnable{
    protected int numberOfDigits;
    protected String formula;
    protected Timer myTimer;
    protected PI piMethod;
    protected double time;
    protected boolean stopped;

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
        stopped = false;
        myTimer.start();
        piMethod.calculatePi(numberOfDigits);
        time = myTimer.stop()/1000000000.0;
        System.out.println("TIME: " + time);
    }

    @Override
    public void run(Object ... params) throws MethodNotImplementedException{
        throw new MethodNotImplementedException("run(Object ... params) not implemented");
    }

    @Override
    public void stop() {
        stopped = true;
        piMethod.stop();
    }

    @Override
    public void warmUp() {
        piMethod.calculatePi(5000);
    }

    public boolean check(){
        if (stopped){
            return true;
        }
        else return false;
    }

    public double getResult(){
        double Score = Math.round((numberOfDigits/time)/100.0);
        return Score;
    }

    public String getTime(){
        return "Finished in: " + String.format("%.2f", time);
    }
}
