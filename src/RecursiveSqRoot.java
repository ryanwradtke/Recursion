/*
 * Problem 1, page 154.
 */

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class RecursiveSqRoot {

    public static void main(String[] args) {
        Double number = (double) 2531;
        double tolerance = .00000005;
        int sigDigs = 3;

        try {
        System.out.println("The square root of " + number 
                + " with a tolarance of " + tolerance
                + " and expressed in " + sigDigs + " significant digits"
                + " = " + squareRoot(number, tolerance, sigDigs));
        } 
        catch(StackOverflowError e) {
            System.out.println("Value too large or tolerance out of bounds.");
        }
    }

    public static Double squareRoot(Double number) {
        return squareRoot(number, .00005, 4);
    }

    public static Double squareRoot(Double number, double tolerance, int sigDigs) {
        Double answer = null;

        if (tolerance != 0) {
            answer = squareRoot(number, 0, number, tolerance);

            Integer croppedAnswer = cropSigDigs(normalize(answer), sigDigs);
            int exponent = findExponent(answer.intValue(), croppedAnswer.doubleValue(), 0);

            answer = croppedAnswer / Math.pow(10, exponent);
        }

        return answer;
    }

    private static Double squareRoot(Double number, double lowGuess, double highGuess, double tolerance) {
        double newGuess = (lowGuess + highGuess) / 2;
        if ((highGuess - newGuess) / newGuess < tolerance) {
            return newGuess;
        } else if (newGuess * newGuess > number) {
            return squareRoot(number, lowGuess, newGuess, tolerance);
        } else if (newGuess * newGuess < number) {
            return squareRoot(number, newGuess, highGuess, tolerance);
        } else {
            return newGuess;
        }
    }

    /**
     * Crops Significant Digits at specified #.
     * @param answer
     * @param sigDigs
     * @return 
     */
    private static Integer cropSigDigs(Double answer, int sigDigs) {
        return (sigDigs == 0) ? answer.intValue() : cropSigDigs(answer * 10, sigDigs - 1);
    }

    /**
     * Rounds significant digits at specified #.
     * @param answer
     * @param sigDigs
     * @return 
     */
    private static Integer roundSigDigs(Double answer, int sigDigs) {
        if (sigDigs == 0) {
            Integer iPart = answer.intValue();
            Double dPart = answer - iPart;
            if (dPart > .49999999999999) {
                answer += 1;
            }
            return answer.intValue();
        }
        return roundSigDigs(answer * 10, sigDigs - 1);
    }

    /**
     * Normalizes the number so it can be cropped or rounded.
     * @param answer
     * @return 
     */
    private static Double normalize(Double answer) {
        return (answer.intValue() == 0) ? answer : normalize(answer / 10);
    }

    /**
     * Finds the proper exponent ( * 10^x ) to de-normalize the number. 
     * @param answer
     * @param cropAnswer
     * @param power
     * @return 
     */
    private static int findExponent(int answer, Double cropAnswer, int exp) {
        return (answer == cropAnswer.intValue()) ? exp : findExponent(answer, cropAnswer / 10, exp + 1);
    }
}