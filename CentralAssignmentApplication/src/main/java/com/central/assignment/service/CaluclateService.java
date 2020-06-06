package com.central.assignment.service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CaluclateService {
	private final String JS = "JavaScript";
	Logger logger = LoggerFactory.getLogger(CaluclateService.class);
		
	/*
	 * In case if we take expression as String 
	 */
	
	public Object caluclate(String expression) {
		Object obj = "Invalid Expression";
		return caluclateExpression(expression,obj);
	}
	
	/*
	 * To prepare & evaluate expression using operands and operator Arrays
	 * Example Arrays
	 * operands = {5,6,9,2}
	 * operators = {"*","+","-"} 
	 * operators length should be less than operands length
	 */
	
	public Object caluclate(int[] operands, char[] operators) {
		Object obj = "Invalid Expression";
		if (operators.length > operands.length && operators.length - 1 != operands.length)
			return obj;
		String expression = "";
		
		for (int i = 0; i < operands.length; i++) {
			if (i < operators.length)
				expression = expression + operands[i] + "" + operators[i];
			else
				expression = expression + operands[i];
		}
		
		return caluclateExpression(expression,obj);
	}
	
	/*
	 * method to evaluate expression without using loops
	 */
	
	private Object caluclateExpression(String expression, Object obj) {
		ScriptEngineManager scriptmanager = new ScriptEngineManager();
		ScriptEngine engine = scriptmanager.getEngineByName(JS);

		try {
			obj =  engine.eval(expression);
		} catch (ScriptException e) {
			logger.error("caluclateExpression :Cannot caluclate Invalid expression");
		}
		return obj;
	}
	
	/*
	 * method to evaluate expression with loops
	 * As discussed during the interview, 
	 * should not use many loops but I'm giving this as one sort of solution ..
	 * I did not use this in API..
	 */
	
	public static int caluclateExpression(String expression) {
		int prevOperand = 0;
		int result = 0;
		int sign = 1;
		int multOrDiviOperator = -1;

		for (int i = 0; i < expression.length(); i++) {
			char currentChar = expression.charAt(i);
			if (Character.isDigit(currentChar)) {
				int num = currentChar - '0';
				while (++i < expression.length() && Character.isDigit(expression.charAt(i))) {
					num = num * 10 + expression.charAt(i) - '0';
				}
				if (multOrDiviOperator == 0) {
					prevOperand = prevOperand * num;
					multOrDiviOperator = -1;
				} else if (multOrDiviOperator == 1) {
					prevOperand = prevOperand / num;
					multOrDiviOperator = -1;
				} else {
					prevOperand = num;
				}
				i--;
			} else if (currentChar == '/') {
				multOrDiviOperator = 1;
			} else if (currentChar == '*') {
				multOrDiviOperator = 0;
			} else if (currentChar == '+') {
				result = result + sign * prevOperand;
				sign = 1;
			} else if (currentChar == '-') {
				result = result + sign * prevOperand;
				sign = -1;
			}
		}

		result = result + sign * prevOperand;
		return result;
	}
}
