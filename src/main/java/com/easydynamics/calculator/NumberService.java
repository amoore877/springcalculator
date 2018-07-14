package com.easydynamics.calculator;

public class NumberService {
	public static final String ZERO = "0";

	public static String normalize(String numberString) throws IllegalArgumentException {
		if (numberString == null) {
			return ZERO;
		}

		numberString = numberString.trim();
		if (numberString.isEmpty())
			return ZERO;

		if (!numberString.matches("[0-9]+")) {
			throw new IllegalArgumentException("The numberString is not valid");
		}

		return numberString;
	}

	/**
	 * Based on assumption that built-in number conversion should not be used at
	 * all, return the corresponding digit.
	 * 
	 * Assumes that input is a valid integer (comes from
	 * {@link #normalize(String)} output.
	 * 
	 * @param numberString
	 * @return
	 */
	public static int convert(char numberString) {
		return numberString - 48;
	}

	public static String rightPadZeroes(String s, int zeroes) {
		StringBuilder sb = new StringBuilder(s);

		while (zeroes > 0) {
			sb.append("0");
			zeroes--;
		}

		return sb.toString();
	}

	/**
	 * Remove left padded zeroes, but ensure that at least one digit remains.
	 * 
	 * @param s
	 * @return
	 */
	public static String removeLeftPadZeroes(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}
}