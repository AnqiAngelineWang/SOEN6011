/**
 * @author Anqi Wang
 */
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class AssertTests {

    boolean check1 = F8_Beta_Function.checkException(1.4);


    /**
     * Test if Gamma value is same with method call, compare with here provide a exact given value
     */
    @Test
    public void testAssertNotEquals() {

        assertNotEquals(F8_Beta_Function.gamma(3.5), 1.2009736023470743);
    }


    /**
     * Test if LogGamma value is same with method call, compare with here provide a exact given value
     */
    @Test
    public void testAssertEqual() {
        Double expected = F8_Beta_Function.logGamma(3.5);
        Double actual = 1.2009736023470743;

        assertEquals("Check LogGamma(x) answer", expected, actual);
    }

    /**
     * Provide a negative input value and see try catch throw exception, or vice versa.
     */
    @Test
    public void testException() {
        boolean check1 = F8_Beta_Function.checkException(0.1);
        assertFalse("Valid input", check1 == false);

    }

    /**
     * Test if the input value is valid
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIllegalArgumentException1() {
        thrown.expect(IllegalArgumentException.class);
        //you can test the exception message like
        thrown.expectMessage('\n' + "The input value is too large that has out of the machine memory");
        F8_Beta_Function.checkException(Math.pow(999999999, 999999999));
    }

    /**
     * Test if the input value is valid
     */
    @Test
    public void testIllegalArgumentException2() {
        thrown.expect(IllegalArgumentException.class);
        //you can test the exception message like
        thrown.expectMessage('\n' + "The input value is too large that that the system couldn't handle");
        F8_Beta_Function.checkException(Math.exp(309));
    }
}


