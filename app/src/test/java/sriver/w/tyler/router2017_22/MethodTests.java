package sriver.w.tyler.router2017_22;

import org.junit.Test;

import sriver.w.tyler.router2017_22.support.Utilities;

import static org.junit.Assert.*;

/**
 * Created by tyler.w.sriver on 1/25/17.
 *
 * Class to test methods
 */
public class MethodTests {
    @Test
    public void testPadHexString() throws Exception {
        assertEquals("000F", Utilities.padHexString("F", 2));  // Test adding 0's
        assertEquals("000F", Utilities.padHexString("000F",2)); // Test to make sure none added
    }
}
