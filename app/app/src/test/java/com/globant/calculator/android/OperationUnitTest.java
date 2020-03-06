package com.globant.calculator.android;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperationUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subtraction_isCorrect() {
        assertEquals(4, 6 - 2);
    }

    @Test
    public void multiply_isCorrect() {
        assertEquals(4, 2 * 2);
    }

    @Test
    public void divide_isCorrect() {
        assertEquals(4, 8 / 2);
    }

    @Test
    public void just_first_number_isCorrect() {
        assertEquals(10, 10);
    }

    @Test
    public void just_dot_as_value() {
        assertEquals("Invalid operation", "." + 2);
    }

    @Test
    public void just_dot_as_second_value() {
        assertEquals("Invalid operation", 2 + ".");
    }

    @Test
    public void divided_by_zero() {
        // assertArrayEquals("Invalid operation", 2 / 0);
    }

    @Test
    public void second_value_is_empty() {
        assertEquals("Invalid operation", 2 + "");
    }
}