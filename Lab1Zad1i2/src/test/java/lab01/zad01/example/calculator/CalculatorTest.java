package lab01.zad01.example.calculator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class CalculatorTest {

	//class under test
	private Calculator calc = new Calculator();
	
	@Test
	public void addingTest() {
		assertNull((calc.add(1, 1) == 2 ? null : 1));
	}
	@Test
	public void substractingTest() {
		assertEquals(5, calc.sub(7, 2));
	}
	@Test
	public void multiplyingTest() {
		assertEquals(6, calc.multi(3, 2));
	}
	@Test(expected = ArithmeticException.class)
	public void div0Test() {
		calc.div(6, 0);
	}
	@Test
	public void divTest() {
		assertThat(calc.div(6, 2), is(equalTo(3)));
	}
	@Test
	public void greaterTest() {
		assertThat(calc.greater(3, 6), is(equalTo(false)));
	}

}
