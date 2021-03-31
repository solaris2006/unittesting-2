package junit;

import static org.siit.BinaryOperator.ADD;
import static org.siit.BinaryOperator.DIVIDE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.sun.tools.javac.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.siit.Expression;
import org.siit.ExpressionEvaluator;
import org.siit.StringExpression;
import org.siit.ValidationException;

public class ExpressionParserTest {


	
	@Test
	public void constantTest() {
		StringExpression e = new StringExpression("0");
		Assert.assertEquals(Arrays.asList(0), e.getElements());
	}
	
	@Test
	public void multiDigitConstantTest() {
		StringExpression e = new StringExpression("44534");
		Assert.assertEquals(Arrays.asList(44534), e.getElements());
	}

	@Test
	public void oneBinaryOperandTest() {
		StringExpression e = new StringExpression("3 + 2");
		Assert.assertEquals(
				Arrays.asList(3, ADD, 2),
				e.getElements());

	}
	
	@Test(expected = ValidationException.class)
	public void incorrectNumberThrowsException() {
		new StringExpression("abc");
	}
	
	@Test(expected = ValidationException.class)
	public void incorrectNumberInExpressionThrowsException() {
		new StringExpression("12 + abc + 4");
	}
	
	//add validation in StringExpression so the test passes
	@Test(expected = ValidationException.class)
	public void incorrectNumberOfTokensThrowsException() {
		new StringExpression("12 +");
	}
	
	//implement parsing with enums instead of string for the test to pass 
	@Test
	public void exampleExpressionWithEnum() {
		StringExpression e = new StringExpression("8 / 2");
		Assert.assertEquals(
				Arrays.asList(8, DIVIDE, 2),
				e.getElements());
	}
	
	//when parsing with enums make sure unknown operands throw exception
	@Test(expected =ValidationException.class)
	public void incorrectOperanShouldThrowException() {
		new StringExpression("8 abc 2");
	}
	
	//implement the following tests
	//that were moved from evaulator test class

	@Test
	public void testOperatorAndMultipleSpaces() {
		StringExpression e = new StringExpression("45    + 2");
		Assert.assertEquals(
				Arrays.asList(45, ADD, 2), e.getElements());
	}

	@Test
	public void testConstantWithWhitespace() {
		StringExpression e = new StringExpression("   324 ");
		Assert.assertEquals(Arrays.asList(324), e.getElements());

	}



}
