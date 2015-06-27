package core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrinterTest {

    public static final String EXPECTED = "1,2,3,12,13,18,33,43,45";
    public static final Integer[] INPUT = new Integer[]{3, 18, 13, 12, 2, 33, 45, 43, 1};
    private static final String EMPTY_STRING = "";

    private Printer printer;

    @Before
    public void initPrinter() {
        printer = new PrinterImpl();
    }

    @Test
    public void testAsSortedString() {
        assertEquals(EXPECTED, printer.asSortedString(INPUT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAsSortedStringEmpty() {
        printer.asSortedString(new Integer[10]);
    }

    @Test
    public void testEmptyArray() {
        assertEquals(EMPTY_STRING, printer.asSortedString(new Integer[0]));
    }

}
