package benchmark;

import exceptions.MethodNotImplementedException;

public interface IBenchmark {
    void initialize(Object ... params);
    void run();
    void run(Object ... params) throws MethodNotImplementedException;
    void stop();
    void warmUp();
}