package org.siit;


import java.util.List;


public class ExpressionEvaluator {
	
	public static int evaluate(Expression expression) {
		List<Object> elements = expression.getElements();

		int result = (int) elements.get(0);
		for (int i=0; i<(elements.size()-1)/2; ++i) {
			result = evalBinary(
					parseOperator(elements.get(i*2+1).toString()),
					result, 
					(int) elements.get(i*2+2));
		}
		return result;
	}
	
	public static Integer evalBinary(
			String operator, Integer left, Integer right) {
		switch (operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			try {
				return  left / right;
			}catch (ArithmeticException e){
				throw new ValidationException("Division by zero not supported");
			}
		case "%":
			return left % right;
		default:
			throw new ValidationException(
					"Operator '" + operator + "' is not known");
		}
	}
	
	public static Integer evalBinary (
			BinaryOperator op, Integer left, Integer right) {
		switch (op) {
		case ADD:
			return left + right;
		case SUBSTRACT:
			return left - right;
		case MULTIPLY:
			return left * right;
		case DIVIDE:
			try {
				return  left / right;
			}catch (ArithmeticException e){
				throw new ValidationException("Division by zero not supported");
			}
		case MODULUS:
			return left % right;
		default:
			throw new ValidationException(
					"Operator '" + op + "' is not known");
		}
	}

	public static BinaryOperator  parseOperator(String op){
		for (BinaryOperator operator : BinaryOperator.values()){
			if (operator.toString().equals(op)){
				return operator;
			}
		}

		throw new ValidationException("Operator '" + op + "' is not known");
	}
	
}
