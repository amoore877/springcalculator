package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberServiceTest {

	@Test
	public void convertNumbers() {
		for (char i = 48; i <= 57; i++) {
			assertTrue(i + " did not convert correctly",
					NumberService.convert(i) == Integer.parseInt(String.valueOf(i)));
		}
	}

	@Test
	public void normalizeNull() {
		assertTrue("Null did not normalize to '0'", NumberService.normalize(null).equals("0"));
	}

	@Test
	public void normalizeEmpty() {
		assertTrue("Empty did not normalize to '0'", NumberService.normalize("").equals("0"));
	}

	@Test
	public void normalizeNumbersWithSpace() {
		for (int i = 0; i <= 9; i++) {
			assertTrue(i + " did not normalize correctly",
					NumberService.normalize(" " + String.valueOf(i) + " ").equals(String.valueOf(i)));
		}
	}

	@Test
	public void normalizeMultipleNumbers() {
		assertTrue("12345 did not normalize correctly", NumberService.normalize("12345").equals("12345"));
	}
}
