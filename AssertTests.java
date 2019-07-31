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
     * Test case ID: 1
     * Requirement 1 has matched with Test case 1: check if the input type is invalid
     */
    @Test
    public void testValidInput1() {
        assertEquals(false, F8_Beta_Function.isNumber("Hello"));
        assertEquals(true, F8_Beta_Function.isNumber("90.3"));
        assertEquals(false, F8_Beta_Function.isNumber("@.&"));
    }

    //Test case ID: 1, continue
    @Test
    public void testValidInput2() {
        boolean check2 = F8_Beta_Function.checkException(1.0);
        assertFalse("Valid input", check1 == false);

    }

    /**
     * Test case ID: 2
     * Requirement 2 has matched with Test case 2: check if the input number is not in the Beta Function's acceptable range
     * Provide a negative input value and see try catch throw exception, or vice versa.
     */
    @Test
    public void testException() {
        boolean check1 = F8_Beta_Function.checkException(0.1);
        assertFalse("Valid input in the Beta Function acceptable range", check1 == false);
    }

    /**
     * Test case ID: 3
     * Requirement 3 has matched with Test case 3: check if user has input a complex number
     * You can test the exception message
     */
    @Test
    public void testIllegalArgumentException1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage('\n' + "The system couldn't calculate complex number, only rational number");
        F8_Beta_Function.checkException(Math.PI);

    }


    /**
     * Test case ID: 4
     * Requirement 4 has matched with Test case 4: check if the input value is too large that has out of the machine memory
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIllegalArgumentException2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage('\n' + "The input value is too large that has out of the machine memory");
        F8_Beta_Function.checkException(Math.pow(999999999, 999999999));
    }

    /**
     * Test case ID: 5
     * Requirement 5 has matched with Test case 5: check if input a big number that the system couldn't handle
     */
    @Test
    public void testIllegalArgumentException3() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage('\n' + "The input a big number that the system couldn't handle");
        F8_Beta_Function.checkException(Math.pow(10, 307));

    }


    /**
     * Test case ID: 6
     * Requirement 6 has matched with Test case 6: check if the first input has been modified again after user's first input
     */
    @Test
    public void testAssertEqual() {
        Double expected = F8_Beta_Function.logGamma(3.5);
        Double actual = 1.2009736023470743;
        assertEquals("Check if the first input has been modified", expected, actual);
    }


    /**
     * Extra Test Case
     * Check correctness
     */
    @Test
    public void testAssertNotEquals() {

        assertNotEquals(F8_Beta_Function.gamma(3.5), 1.2009736023470743);
    }
}


