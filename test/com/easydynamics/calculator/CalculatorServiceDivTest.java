package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorServiceDivTest {

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
	public void divBy0() {
		assertTrue("Division by 0 did not return correctly",
				CalculatorService.divide("12", "0").equals(CalculatorService.DIVISION_BY_0));
	}

	@Test
	public void divBy1() {
		assertTrue("12 / 1 != 12", CalculatorService.divide("12", "1").equals("12"));
	}

	@Test
	public void div0() {
		assertTrue("0 / 1 != 0", CalculatorService.divide("0", "1").equals("0"));
	}

	@Test
	public void divNoR() {
		assertTrue("9 / 3 != 3", CalculatorService.divide("9", "3").equals("3"));
	}

	@Test
	public void divWithR() {
		assertTrue("16 / 3 != 5R1", CalculatorService.divide("16", "3").equals("5R1"));
	}

	@Test
	public void divAllR() {
		assertTrue("3 / 16 != 0R3", CalculatorService.divide("3", "16").equals("0R3"));
	}

	@Test
	public void divLarge() {
		assertTrue("463234 / 4624 != 100R834", CalculatorService.divide("463234", "4624").equals("100R834"));
	}

	@Test
	public void divLarger() {
		assertTrue("573446847163693234 / 4640462844 != 123575355R3852083614",
				CalculatorService.divide("573446847163693234", "4640462844").equals("123575355R3852083614"));
	}
}
