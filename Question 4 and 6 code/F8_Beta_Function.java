/**
 * @author Anqi Wang
 */
public class F8_Beta_Function {


    /**
     *  The function calculates Gamma.
     *  Note that the functions Gamma and LogGamma are mutually dependent.
     * @param x This is the user input value
     * @return double This is the answer return type for this program
     */
    public static double gamma(double x) {  // We require x > 0
        checkException(x);
        // Split the function domain into three intervals:
        // (0, 0.001), [0.001, 12), and (12, infinity)

        ///////////////////////////////////////////////////////////////////////////
        // First interval: (0, 0.001)
        // For small x, 1/Gamma(x) has power series x + gamma x^2  - ...
        // So in this range, 1/Gamma(x) = x + gamma x^2 with error on the order of x^3.
        // The relative error over this interval is less than 6e-7.

        final double gamma = 0.577215664901532860606512090; // Euler's gamma constant

        if (x < 0.001)
            return 1.0 / (x * (1.0 + gamma * x));

        ///////////////////////////////////////////////////////////////////////////
        // Second interval: [0.001, 12)

        if (x < 12.0) {
            // The algorithm directly approximates gamma over (1,2) and uses
            // reduction identities to reduce other arguments to this interval.

            double y = x;
            int n = 0;
            boolean arg_was_less_than_one = (y < 1.0);

            // Add or subtract integers as necessary to bring y into (1,2)
            // Will correct for this below
            if (arg_was_less_than_one) {
                y += 1.0;
            } else {
                n = (int) (Math.floor(y)) - 1;  // will use n later
                y -= n;
            }

            // numerator coefficients for approximation over the interval (1,2)
            double[] p =
                    {
                            -1.71618513886549492533811E+0,
                            2.47656508055759199108314E+1,
                            -3.79804256470945635097577E+2,
                            6.29331155312818442661052E+2,
                            8.66966202790413211295064E+2,
                            -3.14512729688483675254357E+4,
                            -3.61444134186911729807069E+4,
                            6.64561438202405440627855E+4
                    };

            // denominator coefficients for approximation over the interval (1,2)
            double[] q =
                    {
                            -3.08402300119738975254353E+1,
                            3.15350626979604161529144E+2,
                            -1.01515636749021914166146E+3,
                            -3.10777167157231109440444E+3,
                            2.25381184209801510330112E+4,
                            4.75584627752788110767815E+3,
                            -1.34659959864969306392456E+5,
                            -1.15132259675553483497211E+5
                    };

            double num = 0.0;
            double den = 1.0;
            int i;

            double z = y - 1;
            for (i = 0; i < 8; i++) {
                num = (num + p[i]) * z;
                den = den * z + q[i];
            }
            double result = num / den + 1.0;

            // Apply correction if argument was not initially in (1,2)
            if (arg_was_less_than_one) {
                // Use identity gamma(z) = gamma(z+1)/z
                // The variable "result" now holds gamma of the original y + 1
                // Thus we use y-1 to get back the orginal y.
                result /= (y - 1.0);
            } else {
                // Use the identity gamma(z+n) = z*(z+1)* ... *(z+n-1)*gamma(z)
                for (i = 0; i < n; i++)
                    result *= y++;
            }

            return result;
        }

        ///////////////////////////////////////////////////////////////////////////
        // Third interval: [12, infinity)

        if (x > 171.624) {
            // Correct answer too large to display.
            return Double.POSITIVE_INFINITY;
        }

        return Math.exp(logGamma(x));
    }

    /**
     *  The function calculates LogGamma.
     * @param x This is the user input value
     * @return double This is the answer return type for this program
     */
    public static double logGamma(double x) {
        // x must be positive
        checkException(x);
//        if (x <= 0.0) {
//            String msg = String.format("Invalid input argument {0}. Argument must be positive.", x);
//            throw new IllegalArgumentException(msg);
//        }

        if (x < 12.0) {
            return Math.log(Math.abs(gamma(x)));
        }


        double[] c =
                {
                        1.0 / 12.0,
                        -1.0 / 360.0,
                        1.0 / 1260.0,
                        -1.0 / 1680.0,
                        1.0 / 1188.0,
                        -691.0 / 360360.0,
                        1.0 / 156.0,
                        -3617.0 / 122400.0
                };
        double z = 1.0 / (x * x);
        double sum = c[7];
        for (int i = 6; i >= 0; i--) {
            sum *= z;
            sum += c[i];
        }
        double series = sum / x;

        double halfLogTwoPi = 0.91893853320467274178032973640562;
        double logGamma = (x - 0.5) * Math.log(x) - x + halfLogTwoPi + series;
        return logGamma;
    }

    /**
     *  The function checks if the input value is valid and throw exceptions.
     * @param x This is the user input value
     * @return boolean This is the check result type
     */
    public static boolean checkException(double x) {
        try {
            if (x <= 0.0) {
                String msg1 = '\n' + "Exception handling message:";
                String msg2 = String.format("Invalid input value. Value must be positive.", x);
                throw new IllegalArgumentException(msg1 + '\n' + msg2);
            }
            if (x == Math.PI) {
//            if (x > Math.exp(308)) {
                throw new IllegalArgumentException('\n' + "The system couldn't calculate complex number, only real number");
            }
            if (x >= Math.pow(9999,99999)) {
                throw new IllegalArgumentException('\n' + "The input a big number that the system couldn't handle");

            }
            if (x >= Math.pow(999999999, 999999999)) {
                throw new IllegalArgumentException('\n' + "The input value is too large that has out of the machine memory");

            }

        } catch(NullPointerException e) {
            System.out.println("Not valid");
        }
        return true;
    }

    /**
     *  The function records input values into an array
     * @return double[] This is the answer return type to continue do calculation
     */
    public static double[] input() {
        double x = 13.5;
        double y = 17.9;
//        double x = 171.623999999999999;
//        double x = Math.pow(10,2);
//        double x = 171.8;
//        double y = Math.PI;
//        double y = (1/97);

        double[] array = new double[2];


        array[0] = x;
        array[1] = y;

        return array;
    }

    /**
     * This is the main function for this program
     */
    public static void main(String[] args) {

//        System.out.println( logGamma(3.5));
        double[] numbers = input();

        System.out.println("Gamma x: " + gamma(numbers[0]));
        System.out.println("Gamma y: " + gamma(numbers[1]));
//       if ( (numbers[0] < 171.624) && (numbers[1] < 171.624) ) {
        System.out.println("Beta (x, y): " + (gamma(numbers[0]) * gamma(numbers[1])) / gamma(numbers[0] + numbers[1]));

//       } else {
//           System.out.println("The Beta answer is infinity, NaN.");
//       }


    }

}

/**
 * Reference
 * <p>
 * http://www.johndcook.com/stand_alone_code.html for the source of this code and more like it.
 * https://hewgill.com/picomath/java/Gamma.java.html
 * http://www.meracalculator.com/math/beta-function.php
 */
