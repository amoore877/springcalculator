package com.easydynamics.calculator;

public class CalculatorService {

	public static final String DIVISION_BY_0 = "Division by 0!";

	/**
	 * Assumes input has been normalized.
	 * 
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public static String add(String a, String b) {
		// If one String is “0”, return the other string.
		if (a.equals("0")) {
			return b;
		}
		if (b.equals("0")) {
			return a;
		}

		/*
		 * Starting from the very right of both Strings (the ones column), add
		 * the numbers, keeping track of potential carry to bring to the next
		 * digits (the tens column), and so on, until one Strings has been fully
		 * exhausted and no carry remains, or both Strings are exhausted.
		 * 
		 * If both Strings are exhausted, add any remaining carry at the end.
		 * 
		 * If only one String is exhausted, prepend the rest of the other
		 * String.
		 * 
		 * Each iteration prepends the resulting add to the current String. For
		 * better processing, use StringBuilder rather than concatenating
		 * Strings continuously.
		 */

		StringBuilder result = new StringBuilder();

		int aInd = a.length() - 1;
		int bInd = b.length() - 1;

		boolean carry = false;
		while (// end if both Strings exhausted
		!(aInd < 0 && bInd < 0)
				// and
				&&
				// end if one String exhausted and no carry
				!((aInd < 0 || bInd < 0) && !carry)) {
			int sum = carry ? 1 : 0;

			if (aInd >= 0) {
				sum += NumberService.convert(a.charAt(aInd));
			}

			if (bInd >= 0) {
				sum += NumberService.convert(b.charAt(bInd));
			}

			if (sum >= 10) {
				sum -= 10;
				carry = true;
			} else {
				carry = false;
			}

			result.insert(0, sum);

			// decrement both indices
			aInd--;
			bInd--;
		}

		// any leftover carry? (only if both strings exhausted)
		if (carry) {
			result.insert(0, "1");
		} else if (aInd >= 0) {
			// some a left
			result.insert(0, a.substring(0, aInd + 1));
		} else if (bInd >= 0) {
			// some b left
			result.insert(0, b.substring(0, bInd + 1));
		}

		System.out.println("Returning: " + a + "+" + b + "=" + result.toString());
		return result.toString();
	}

	/**
	 * Assumes input has been normalized.
	 * 
	 * @param a
	 * @param b
	 * @return a-b
	 */
	public static String subtract(String a, String b) {
		// If a is “0”, and b is not, return negative b
		if (a.equals("0") && !b.equals("0")) {
			return "-" + b;
		}

		// If b is “0”, return a.
		if (b.equals("0")) {
			return a;
		}

		/*
		 * Determine the “true” a and b, where a > b. This logic must examine
		 * potentially the entirety of a and b, but the alternative is
		 * potentially equally expensive logic for running into special logic
		 * for fixing a – b where b > a (another call to subtract).
		 * 
		 * From Assumptions, we know neither number has left-padding with 0’s.
		 * If one number is longer than the other, that is the larger.
		 * Otherwise, they are the same length, so start from the left-most
		 * digit of each, iterating until one number is determined to be the
		 * largest.
		 * 
		 * If they are equal, we can return “0” right away.
		 */
		Boolean negativeAns = null;
		if (a.length() > b.length()) {
			negativeAns = false;
		} else if (b.length() > a.length()) {
			negativeAns = true;
		} else {
			for (int i = 0; i < a.length() && negativeAns == null; i++) {
				char aChar = a.charAt(i);
				char bChar = b.charAt(i);
				if (aChar > bChar) {
					negativeAns = false;
				} else if (bChar > aChar) {
					negativeAns = true;
				}
			}

			if (negativeAns == null) {
				// the numbers were equal
				return "0";
			}
		}

		String newA;
		String newB;
		if (negativeAns) {
			newA = b;
			newB = a;
		} else {
			newA = a;
			newB = b;
		}

		/*
		 * We start from the right-most digits of both numbers.
		 * 
		 * From this point forward, “a” refers to the larger of the two numbers,
		 * and “b” to the smaller. When both b (the smaller number) still has a
		 * digit to give: If there is a “subtraction carry”: If a’s digit is not
		 * 0, subtract b’s digit and 1 from a’s digit. Unset “subtraction
		 * carry”. If a’s digit is 0, we know from Assumptions a has another
		 * digit to give, subtract b’s digit from 9. Keep “subtraction carry”
		 * set. If there is not a “subtraction carry”, subtract b’s digit from
		 * a’s. If the result is positive, prepend to the return. If the result
		 * is 0: If either a or b have more digits to work with, prepend to
		 * return. Otherwise prepend nothing. If the result is negative: We know
		 * a is larger than b, so we know a must have at least one more digit
		 * after this one. Set “subtraction carry”. Add 10 to the current
		 * result, and prepend to the return. If a still has digits to give (but
		 * b does not): While there is a “subtraction carry”: If the current
		 * digit of a is 0, prepend 9 to return. Keep “subtraction carry” set.
		 * Otherwise, prepend the digit of a minus 1 to return. Unset
		 * “subtraction carry”. Prepend the remainder of a. If we switched a and
		 * b at the beginning, prepend “-“ to the result.
		 * 
		 */
		StringBuilder result = new StringBuilder();

		int aInd = newA.length() - 1;
		int bInd = newB.length() - 1;

		boolean subCarry = false;

		while (bInd >= 0) {
			// both strings still have a digit to give
			int res;
			int aNum = NumberService.convert(newA.charAt(aInd));
			int bNum = NumberService.convert(newB.charAt(bInd));
			if (subCarry) {
				if (aNum != 0) {
					res = aNum - bNum - 1;
					subCarry = false;
				} else {
					res = 9 - bNum;
				}
			} else {
				res = aNum - bNum;
			}

			if (res >= 0) {
				// non-negative result
				result.insert(0, res);
			} else {
				// negative result
				subCarry = true;
				res += 10;
				result.insert(0, res);
			}

			// decrement indices
			aInd--;
			bInd--;
		}

		// b is done, but a is not
		if (aInd >= 0) {
			while (subCarry) {
				int aNum = NumberService.convert(newA.charAt(aInd));
				if (aNum == 0) {
					result.insert(0, 9);
				} else if ((aNum == 1 && aInd == 0)) {
					subCarry = false;
				} else {
					result.insert(0, aNum - 1);
					subCarry = false;
				}

				aInd--;
			}

			if (aInd >= 0) {
				result.insert(0, newA.substring(0, aInd + 1));
			}
		}

		// remove left-padding of zeroes, if any
		result = new StringBuilder(NumberService.removeLeftPadZeroes(result.toString()));

		if (negativeAns) {
			result.insert(0, "-");
		}

		System.out.println("Returning: " + a + "-" + b + "=" + result.toString());
		return result.toString();
	}

	/**
	 * Assume normalized input.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String multiply(String a, String b) {
		/*
		 * If either a or b is 0, return 0.
		 * 
		 * If a is 1, return b. If b is 1, return a.
		 * 
		 * Keep a result to be continuously added to.
		 * 
		 * For each digit in b, right-pad a temporary version of a with 0’s
		 * equal to b’s digits place (ones is 0, tens is 1,…). Now add that temp
		 * a b-digit times to the result. Return the result.
		 * 
		 */
		if (a.equals("0") || b.equals("0")) {
			return "0";
		}

		if (a.equals("1")) {
			return b;
		}

		if (b.equals("1")) {
			return a;
		}

		String result = "0";

		for (int bInd = b.length() - 1, tens = 0; bInd >= 0; bInd--, tens++) {
			int numB = NumberService.convert(b.charAt(bInd));
			if (numB == 0) {
				continue;
			}

			String paddedA = NumberService.rightPadZeroes(a, tens);

			while (numB > 0) {
				result = add(result, paddedA);

				numB--;
			}
		}

		System.out.println("Returning: " + a + "*" + b + "=" + result);
		return result;
	}

	/**
	 * Assumes input has been normalized.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divide(String a, String b) {
		/*
		 * If b is 0, return “Division by 0!”
		 * 
		 * If b is 1, return a.
		 * 
		 * If a is 0, return “0”.
		 * 
		 * Track a whole number quotient and a remainder value, both starting at
		 * 0. Determine how many more digits a has than b. If the digit
		 * difference is >1: add (difference – 1) to quotient. From a, subtract
		 * the value of b right-padded with (difference – 1) 0’s. Keep
		 * subtracting b from a. Whenever the result is positive or 0, add 1 to
		 * the quotient. When it is negative, stop subtracting and use the last
		 * positive value of a as the remainder. When it is 0, stop subtracting
		 * and use 0 as the remainder. If 0 is the remainder, return just the
		 * quotient. If 0 is not the remainder, return in the format “quotient
		 * Rremainder”
		 * 
		 */
		if (b.equals("0")) {
			return DIVISION_BY_0;
		}

		if (b.equals("1") || a.equals("0")) {
			return a;
		}

		String quotientWholeString = "0";
		String remainder = "0";
		boolean foundNegOrZero = false;
		String newA = a;

		int digitDiff;
		do {
			digitDiff = newA.length() - b.length();
			if (digitDiff > 1) {
				String subResult = divide(newA, NumberService.rightPadZeroes(b, digitDiff - 1));

				String[] splitResult = subResult.split("R");
				quotientWholeString = add(quotientWholeString,
						NumberService.rightPadZeroes(splitResult[0], digitDiff - 1));

				if (splitResult.length > 1) {
					newA = splitResult[1];
				} else {
					newA = "0";
					foundNegOrZero = true;
				}
			}
		} while (!foundNegOrZero && digitDiff > 1);

		int additionalQuotient = 0;
		while (!foundNegOrZero) {
			String currA = subtract(newA, b);

			if (currA.startsWith("-")) {
				foundNegOrZero = true;
				remainder = newA;
			} else if (currA.equals("0")) {
				foundNegOrZero = true;
				additionalQuotient++;
			} else {
				additionalQuotient++;
				newA = currA;
			}
		}
		quotientWholeString = add(quotientWholeString, String.valueOf(additionalQuotient));

		String result;
		if (remainder.equals("0")) {
			result = String.valueOf(quotientWholeString);
		} else {
			result = quotientWholeString + "R" + remainder;
		}

		System.out.println("Returning: " + a + "/" + b + "=" + result);

		return result;
	}
}