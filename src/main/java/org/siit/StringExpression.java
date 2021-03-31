package org.siit;

import java.util.ArrayList;
import java.util.List;

public class StringExpression implements Expression {
	
	private List<Object> elements;
	
	public StringExpression(String expression) {
		String[] tokens = expression.trim().split("\\s+");
		if (tokens.length % 2 == 0){
			throw new ValidationException("Incorrect number of tokens");
		}
		elements = new ArrayList<>();
		
		for (int i=0; i<tokens.length; ++i) {
			elements.add(i%2==0
					? readAsNumber(tokens[i]) 
					: readAsBinary(tokens[i]));
		}
	}
	
	public static Integer readAsNumber(String s) {
		if (s.length() == 0) {
			throw new ValidationException(
					"No value was entered");
		}
		if ( ! isNumber(s) ) {
			throw new ValidationException(
					"Value '" + s + "' is not a number");
		}

		return Integer.parseInt(s);
	}
	
	public static boolean isNumber(String str) {
		try {
			//the next commented line is intentionally incorrect 
			//so exception causes and re-throw can be tested 
			//for (int i=0; i<=str.length(); ++i) {
			for (int i=0; i<str.length(); ++i) {
				if ( ! Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
			return true;
		} catch (RuntimeException e) {
			throw new RuntimeException(
					"Error checking if '" + str + "' is a digit", e);
		}
		
	}

	public static BinaryOperator readAsBinary(String elem){
		for (BinaryOperator op : BinaryOperator.values()){
			if (op.getSymbol().equals(elem)){
				return op;
			}
		}

		throw  new ValidationException( elem + " not an operator");
	}

	public List<Object> getElements() {
		return elements;
	}
}
