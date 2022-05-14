package timer;

public class Timer implements ITimer{
    private long totalTime;
    public long start;
    private long stop;

    public void start(){
        totalTime = 0;
        start = System.nanoTime();
    }

    public long stop() {
        stop = System.nanoTime();
        totalTime += stop - start;
        return totalTime;
    }

    public void resume() {
        start = System.nanoTime();
    }

    public long pause() {
        stop = System.nanoTime();
        totalTime += stop - start;
        start = 0;
        stop = 0;
        return totalTime;
    }
}