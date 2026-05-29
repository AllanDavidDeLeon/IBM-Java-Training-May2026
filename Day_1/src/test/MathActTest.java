package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import day7.src.MathAct;

class MathActTest {

	@Test
	void should_ReturnEqual_IfAddEqual() {
		//given
		float a = 2;
		float b = 4;
		//when
		float sum = MathAct.add(a,b);
		//then
		assertEquals(6,sum);
	}
	
	@Test
	void should_ReturnEqual_IfSubEqual() {
		//given
		float a = 2;
		float b = 4;
		//when
		float diff = MathAct.subtract(b,a);
		//then
		assertEquals(2,diff);
	}
	
	@Test
	void should_ReturnEqual_IfMulEqual() {
		//given
		float a = 2;
		float b = 4;
		//when
		float product = MathAct.multiply(a,b);
		//then
		assertEquals(8,product);
	}
	
	@Test
	void should_ReturnEqual_IfDivEqual() {
		//given
		float a = 2;
		float b = 4;
		//when
		float quotient = MathAct.divide(b,a);
		//then
		assertEquals(2,quotient);
	}
	
	@Test
	void should_ReturnThrow_IfDivbyZero() {
		//given
		float a = 2;
		float b = 0;
		//when
		
        Executable executable = () -> MathAct.divide(a,b);

        // then
        assertThrows(ArithmeticException.class, executable);
	}
}
