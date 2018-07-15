package com.easydynamics.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HomeControllerTest {

	private static HomeController controller;

	private CalculatorCanvas canvas;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		controller = new HomeController();
	}

	@Before
	public void setUp() throws Exception {
		canvas = new CalculatorCanvas();
	}

	public String performAdd(String a, String b) {
		return performOp(a, b, "add");
	}

	public String performSub(String a, String b) {
		return performOp(a, b, "subtract");
	}

	public String performMult(String a, String b) {
		return performOp(a, b, "multiply");
	}

	public String performDiv(String a, String b) {
		return performOp(a, b, "divide");
	}

	public String performOp(String a, String b, String operation) {
		canvas.setA(a);
		canvas.setB(b);
		controller.operation(operation, canvas);
		return canvas.getResult();
	}

	@Test
	public void singleDigitsNoCarry() {
		assertTrue("2 + 3 != 5", performAdd("2", "3").equals("5"));
	}

	@Test
	public void singleDigitsWithCarry() {
		assertTrue("6 + 7 != 13", performAdd("6", "7").equals("13"));
	}

	@Test
	public void multipleDigitsNoCarry() {
		assertTrue("345 + 424 != 769", performAdd("345", "424").equals("769"));
	}

	@Test
	public void multipleDigitsWithCarry() {
		assertTrue("642 + 479 != 1121", performAdd("642", "479").equals("1121"));
	}

	@Test
	public void unevenDigits() {
		assertTrue("263 + 47 != 310", performAdd("263", "47").equals("310"));
	}

	@Test
	public void veryUnevenDigits() {
		assertTrue("64345263 + 47 != 310", performAdd("64345263", "47").equals("64345310"));
	}

	@Test
	public void severalDigits() {
		assertTrue("6446465472395420412 + 6984229642669534367 != 13430695115064954779",
				performAdd("6446465472395420412", "6984229642669534367").equals("13430695115064954779"));
	}

	@Test
	public void divBy0() {
		assertTrue("Division by 0 did not return correctly",
				performDiv("12", "0").equals(CalculatorService.DIVISION_BY_0));
	}

	@Test
	public void divBy1() {
		assertTrue("12 / 1 != 12", performDiv("12", "1").equals("12"));
	}

	@Test
	public void div0() {
		assertTrue("0 / 1 != 0", performDiv("0", "1").equals("0"));
	}

	@Test
	public void divNoR() {
		assertTrue("9 / 3 != 3", performDiv("9", "3").equals("3"));
	}

	@Test
	public void divWithR() {
		assertTrue("16 / 3 != 5R1", performDiv("16", "3").equals("5R1"));
	}

	@Test
	public void divAllR() {
		assertTrue("3 / 16 != 0R3", performDiv("3", "16").equals("0R3"));
	}

	@Test
	public void divLarge() {
		assertTrue("463234 / 4624 != 100R834", performDiv("463234", "4624").equals("100R834"));
	}

	@Test
	public void divLarger() {
		assertTrue("573446847163693234 / 4640462844 != 123575355R3852083614",
				performDiv("573446847163693234", "4640462844").equals("123575355R3852083614"));
	}

	@Test
	public void multA0() {
		assertTrue("0 * 2 != 0", performMult("0", "2").equals("0"));
	}

	@Test
	public void multB0() {
		assertTrue("2 * 0 != 0", performMult("2", "0").equals("0"));
	}

	@Test
	public void multB1() {
		assertTrue("2 * 1 != 2", performMult("2", "1").equals("2"));
	}

	@Test
	public void multA1() {
		assertTrue("1 * 2 != 2", performMult("1", "2").equals("2"));
	}

	@Test
	public void multSingleDigit() {
		assertTrue("5 * 4 != 20", performMult("5", "4").equals("20"));
	}

	@Test
	public void multDoubleDigit() {
		assertTrue("53 * 41 != 2173", performMult("53", "41").equals("2173"));
	}

	@Test
	public void multUnevenADigit() {
		assertTrue("53633 * 41 != 2198953", performMult("53633", "41").equals("2198953"));
	}

	@Test
	public void multUnevenBDigit() {
		assertTrue("41 * 53633 != 2198953", performMult("41", "53633").equals("2198953"));
	}

	@Test
	public void multLargeDigit() {
		assertTrue("4109421 * 536336484 != 2204032410415764",
				performMult("4109421", "536336484").equals("2204032410415764"));
	}

	@Test
	public void multLargerDigit() {
		assertTrue("931141094210403526 * 8743465365854806336484 != 8141399907952810559746575130739376042584",
				performMult("931141094210403526", "8743465365854806336484")
						.equals("8141399907952810559746575130739376042584"));
	}

	@Test
	public void a0b0Test() {
		assertTrue("0 - 0 != 0", performSub("0", "0").equals("0"));
	}

	@Test
	public void a0Test() {
		assertTrue("0 - 2 != -2", performSub("0", "2").equals("-2"));
	}

	@Test
	public void b0Test() {
		assertTrue("2 - 0 != 2", performSub("2", "0").equals("2"));
	}

	@Test
	public void equalTest() {
		assertTrue("5 - 5 != 0", performSub("5", "5").equals("0"));
	}

	@Test
	public void singleDigitPosRes() {
		assertTrue("5 - 3 != 2", performSub("5", "3").equals("2"));
	}

	@Test
	public void singleDigitNegRes() {
		assertTrue("3 - 5 != -2", performSub("3", "5").equals("-2"));
	}

	@Test
	public void doubleDigitPosRes() {
		assertTrue("67 - 32 != 35", performSub("67", "32").equals("35"));
	}

	@Test
	public void doubleDigitNegRes() {
		assertTrue("32 - 67 != -35", performSub("32", "67").equals("-35"));
	}

	@Test
	public void multDigitPosRes() {
		assertTrue("47074 - 46240 != 834", performSub("47074", "46240").equals("834"));
	}

	@Test
	public void unevenA() {
		assertTrue("3529801 - 242 = 3529559", performSub("3529801", "242").equals("3529559"));
	}

	@Test
	public void unevenB() {
		assertTrue("242 - 3529801 = -3529559", performSub("242", "3529801").equals("-3529559"));
	}

	@Test
	public void largeDigitNegRes() {
		assertTrue("674693394092369824 - 3254782355842684267 != -2580088961750314443",
				performSub("674693394092369824", "3254782355842684267").equals("-2580088961750314443"));
	}

	@Test
	public void largeDigitPosRes() {
		assertTrue("3254782355842684267 - 674693394092369824 != 2580088961750314443",
				performSub("3254782355842684267", "674693394092369824").equals("2580088961750314443"));
	}

	@Test
	public void invalidOp() {
		canvas.setA("0");
		canvas.setB("0");
		controller.operation("fshfya", canvas);
		assertTrue("Controller accepted an unknown operation.",
				canvas.getResult().equals(HomeController.UNKNOWN_OPERATION));
	}
}
