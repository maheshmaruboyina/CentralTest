package com.central.assignment.model;

public class CalculateRequest {
	private int[] operands;
	private char[] operators;
	
	public int[] getOperands() {
		return operands;
	}
	public void setOperands(int[] operands) {
		this.operands = operands;
	}
	public char[] getOperators() {
		return operators;
	}
	public void setOperators(char[] operators) {
		this.operators = operators;
	}

}
