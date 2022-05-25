package formulas;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class Geometric extends PI{
    private static final BigDecimal bigTwo = new BigDecimal(2);
    private static final BigDecimal bigFour = new BigDecimal(4);

    private static BigDecimal bigSqrt(BigDecimal bd, MathContext con) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(bd.doubleValue()));
        while (!Objects.equals(x0, x1)) {
            x0 = x1;
            x1 = bd.divide(x0, con).add(x0).divide(bigTwo, con);
        }
        return x1;
    }

    public BigDecimal calculatePi(int digits){
        running = true;
        MathContext precision = new MathContext(digits);
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(bigSqrt(bigTwo, precision), precision);
        BigDecimal t;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;
        while ((!Objects.equals(a, g))) {
            if(running == false){
                break;
            }
            t = a.add(g).divide(bigTwo, precision);
            g = bigSqrt(a.multiply(g), precision);
            a = t;
            pow = pow.multiply(bigTwo);
            sum = sum.add(a.multiply(a).subtract(g.multiply(g)).multiply(pow));
        }
        BigDecimal pi = bigFour.multiply(a.multiply(a)).divide(BigDecimal.ONE.subtract(sum), precision);
        return pi;
    }

    public void stop(){
        running = false;
    }
}
