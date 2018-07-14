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

	@Test
	public void removeNoLeftPad() {
		assertTrue("12632 did not get filtered for extra 0's correctly",
				NumberService.removeLeftPadZeroes("12632").equals("12632"));
	}

	@Test
	public void removeLeftPad() {
		assertTrue("0012632 did not get filtered for extra 0's correctly",
				NumberService.removeLeftPadZeroes("0012632").equals("12632"));
	}

	@Test
	public void removeLeftPadAll0() {
		assertTrue("000000 did not get filtered for extra 0's correctly",
				NumberService.removeLeftPadZeroes("000000").equals("0"));
	}

	@Test
	public void rightPad0() {
		assertTrue("123 with no extra 0's did not get padded correctly",
				NumberService.rightPadZeroes("123", 0).equals("123"));
	}

	@Test
	public void rightPadNeg() {
		assertTrue("123 with negative 0's did not get padded correctly",
				NumberService.rightPadZeroes("123", -2).equals("123"));
	}

	@Test
	public void rightPadPos() {
		assertTrue("123 with 5 0's did not get padded correctly",
				NumberService.rightPadZeroes("123", 5).equals("12300000"));
	}
}
