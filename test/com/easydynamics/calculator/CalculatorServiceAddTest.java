package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculatorServiceAddTest {

	@Test
	public void singleDigitsNoCarry() {
		assertTrue("2 + 3 != 5", CalculatorService.add("2", "3").equals("5"));
	}

	@Test
	public void singleDigitsWithCarry() {
		assertTrue("6 + 7 != 13", CalculatorService.add("6", "7").equals("13"));
	}

	@Test
	public void multipleDigitsNoCarry() {
		assertTrue("345 + 424 != 769", CalculatorService.add("345", "424").equals("769"));
	}

	@Test
	public void multipleDigitsWithCarry() {
		assertTrue("642 + 479 != 1121", CalculatorService.add("642", "479").equals("1121"));
	}

	@Test
	public void unevenDigits() {
		assertTrue("263 + 47 != 310", CalculatorService.add("263", "47").equals("310"));
	}

	@Test
	public void veryUnevenDigits() {
		assertTrue("64345263 + 47 != 310", CalculatorService.add("64345263", "47").equals("64345310"));
	}

	@Test
	public void severalDigits() {
		assertTrue("6446465472395420412 + 6984229642669534367 != 13430695115064954779",
				CalculatorService.add("6446465472395420412", "6984229642669534367").equals("13430695115064954779"));
	}
}
