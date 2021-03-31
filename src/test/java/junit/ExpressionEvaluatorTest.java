package junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.siit.Expression;
import org.siit.ExpressionEvaluator;
import org.siit.StringExpression;
import org.siit.ValidationException;

public class ExpressionEvaluatorTest {

	private static int evaluate(Object... elem) {
		List<Object> elements = Arrays.asList(elem);
		Expression expr = Mockito.mock(Expression.class);
		Mockito.when(expr.getElements()).thenReturn(elements);
		return ExpressionEvaluator.evaluate(expr);
	}
	
	@Test
	public void testZeroConstant() {
		Assert.assertEquals(
				0, evaluate(0));
	}

	@Test
	public void testFiveConstant() {
		Assert.assertEquals(
				5, evaluate(5));
	}
	
	@Test
	public void testMultiDigitConstant() {
		Assert.assertEquals(
				44232, evaluate(44232));
	}
	
	@Test
	public void testSimpleAddition() {
		Assert.assertEquals(
				5,
				evaluate(3, "ADD", 2)
		);
	}


	@Test(expected = ValidationException.class)
	public void testZeroDivision(){
		StringExpression e = new StringExpression(" 2 / 0");
		ExpressionEvaluator.evaluate(e);
	}
}
