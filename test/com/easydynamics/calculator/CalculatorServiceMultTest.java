package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculatorServiceMultTest {

	@Test
	public void multA0() {
		assertTrue("0 * 2 != 0", CalculatorService.multiply("0", "2").equals("0"));
	}

	@Test
	public void multB0() {
		assertTrue("2 * 0 != 0", CalculatorService.multiply("2", "0").equals("0"));
	}

	@Test
	public void multB1() {
		assertTrue("2 * 1 != 2", CalculatorService.multiply("2", "1").equals("2"));
	}

	@Test
	public void multA1() {
		assertTrue("1 * 2 != 2", CalculatorService.multiply("1", "2").equals("2"));
	}

	@Test
	public void multSingleDigit() {
		assertTrue("5 * 4 != 20", CalculatorService.multiply("5", "4").equals("20"));
	}

	@Test
	public void multDoubleDigit() {
		assertTrue("53 * 41 != 2173", CalculatorService.multiply("53", "41").equals("2173"));
	}

	@Test
	public void multUnevenADigit() {
		assertTrue("53633 * 41 != 2198953", CalculatorService.multiply("53633", "41").equals("2198953"));
	}

	@Test
	public void multUnevenBDigit() {
		assertTrue("41 * 53633 != 2198953", CalculatorService.multiply("41", "53633").equals("2198953"));
	}

	@Test
	public void multLargeDigit() {
		assertTrue("4109421 * 536336484 != 2204032410415764",
				CalculatorService.multiply("4109421", "536336484").equals("2204032410415764"));
	}

	@Test
	public void multLargerDigit() {
		assertTrue("931141094210403526 * 8743465365854806336484 != 8141399907952810559746575130739376042584",
				CalculatorService.multiply("931141094210403526", "8743465365854806336484")
						.equals("8141399907952810559746575130739376042584"));
	}
}
