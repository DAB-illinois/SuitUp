package main.java;

import static org.junit.Assert.*;

public class DatabaseItemTest {

    @org.junit.Test
    public void getPrice1() throws Exception {
        String price = "395";
        double expected = 2.0;

        //assertEquals(expected, DatabaseItem.generatePriceValue(price), 0.01);
    }

    @org.junit.Test
    public void getPrice2() throws Exception {
        String price = "20";
        double expected = -2.0;

        //assertEquals(expected, DatabaseItem.generatePriceValue(price), 0.01);
    }

    @org.junit.Test
    public void getPrice3() throws Exception {
        String price = "100";
        double expected = -1.149;

        //assertEquals(expected, DatabaseItem.generatePriceValue(price), 0.01);
    }

}