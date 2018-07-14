package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorServiceSubTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void a0b0Test() {
		assertTrue("0 - 0 != 0", CalculatorService.subtract("0", "0").equals("0"));
	}

	@Test
	public void a0Test() {
		assertTrue("0 - 2 != -2", CalculatorService.subtract("0", "2").equals("-2"));
	}

	@Test
	public void b0Test() {
		assertTrue("2 - 0 != 2", CalculatorService.subtract("2", "0").equals("2"));
	}

	@Test
	public void equalTest() {
		assertTrue("5 - 5 != 0", CalculatorService.subtract("5", "5").equals("0"));
	}

	@Test
	public void singleDigitPosRes() {
		assertTrue("5 - 3 != 2", CalculatorService.subtract("5", "3").equals("2"));
	}

	@Test
	public void singleDigitNegRes() {
		assertTrue("3 - 5 != -2", CalculatorService.subtract("3", "5").equals("-2"));
	}

	@Test
	public void doubleDigitPosRes() {
		assertTrue("67 - 32 != 35", CalculatorService.subtract("67", "32").equals("35"));
	}

	@Test
	public void doubleDigitNegRes() {
		assertTrue("32 - 67 != -35", CalculatorService.subtract("32", "67").equals("-35"));
	}

	@Test
	public void multDigitPosRes() {
		assertTrue("47074 - 46240 != 834", CalculatorService.subtract("47074", "46240").equals("834"));
	}

	@Test
	public void unevenA() {
		assertTrue("3529801 - 242 = 3529559", CalculatorService.subtract("3529801", "242").equals("3529559"));
	}

	@Test
	public void unevenB() {
		assertTrue("242 - 3529801 = -3529559", CalculatorService.subtract("242", "3529801").equals("-3529559"));
	}

	@Test
	public void largeDigitNegRes() {
		assertTrue("674693394092369824 - 3254782355842684267 != -2580088961750314443",
				CalculatorService.subtract("674693394092369824", "3254782355842684267").equals("-2580088961750314443"));
	}

	@Test
	public void largeDigitPosRes() {
		assertTrue("3254782355842684267 - 674693394092369824 != 2580088961750314443",
				CalculatorService.subtract("3254782355842684267", "674693394092369824").equals("2580088961750314443"));
	}
}
