package com.easydynamics.calculator;

public class CalculatorCanvas {

    private String result;
    private String a;
    private String b;

    public CalculatorCanvas() {
        result = NumberService.ZERO;
        a = NumberService.ZERO;
        b = NumberService.ZERO;
    }

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the a
	 */
	public String getA() {
		return a;
	}
	/**
	 * @param a the a to set
	 */
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @return the b
	 */
	public String getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(String b) {
		this.b = b;
	}
    

}