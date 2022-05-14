package formulas;

public class Score {
    private static int score;

    public static double calculateScore(int digits, double time){
        score = (int) (digits/time);
        return score;
    }
}
