package com.example.orderup.Objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiftcardTest {

    @Test
    public void testGetAmount() {
        Giftcard gc = new Giftcard("1234567890", 50.0f);
        float expected = 50.0f;
        float actual = gc.getAmount();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testGetNumber() {
        Giftcard gc = new Giftcard("1234567890", 50.0f);
        String expected = "1234567890";
        String actual = gc.getNumber();
        assertEquals(expected, actual);
    }
}
