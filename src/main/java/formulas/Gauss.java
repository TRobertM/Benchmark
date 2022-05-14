package formulas;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Gauss extends PI{
    public static int scale;

    public BigDecimal calculatePi(int digits) {
        scale = digits;
        running = true;
        MathContext mc = new MathContext(scale);

        BigDecimal two = new BigDecimal(2.0);

        BigDecimal aZero = new BigDecimal(1.0);
        BigDecimal bZero = new BigDecimal(1.0).divide(new BigDecimal(2.0).sqrt(mc), scale, RoundingMode.CEILING);
        BigDecimal tZero = new BigDecimal(1.0).divide(new BigDecimal(4.0));
        BigDecimal pZero = new BigDecimal(1.0);
        BigDecimal an1, bn1, tn1, pn1;
        BigDecimal pi = new BigDecimal(0.0);
        for (long i = 0; i < 25 && running; ++i) {
            an1 = aZero.add(bZero, mc).divide(two, scale, RoundingMode.CEILING);
            bn1 = aZero.multiply(bZero, mc).sqrt(mc);
            tn1 = tZero.subtract(pZero.multiply(aZero.subtract(an1, mc).pow(2, mc), mc), mc);
            pn1 = pZero.multiply(two, mc);
            pi =  aZero.add(bZero, mc).pow(2, mc).divide(tZero.multiply(two, mc), scale, RoundingMode.CEILING);
            aZero = an1;
            bZero = bn1;
            tZero = tn1;
            pZero = pn1;
        }
        return pi;
    }

    public void stop(){
        running = false;
    }
}
