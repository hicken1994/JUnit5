package Junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    private Calculator calculatorNull;
    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }
    @AfterEach
    public void tearDown(){
        calculator= null;
        System.out.println("@AfterEach -> tearDown()");
    }

    @Test
    public void calculatorNotNullTest(){
    assertNotNull(calculator, "Calculator debe ser not null, por favor instanciarlo");
    System.out.println("@Test -> calculatorNotNullTest()");

}
     @Test
    public void calculatorNullTest(){
        assertNull(calculatorNull);
         System.out.println("@Test -> calculatorNullTest()");

     }
     @Test
    public void addAssertTest(){
        //1. SetUp
         Calculator C_assert = new Calculator();
         int resultadoEsperado = 30;
         int resultadoActual;

         // 2. Action

         resultadoActual = C_assert.add(10,20);

         //3. Assert
         assertEquals(resultadoEsperado, resultadoActual,"Funciono OK");
         System.out.println(" @Test -> addAssertTest()");
     }
     @Test
    public void addTest(){
        assertNotEquals(40,calculator.add(10,20));
         System.out.println("@Test -> addTest");
     }

@Test
public void assertTypesTest(){
    assertTrue(Integer.valueOf(10) instanceof Integer, "Si es Integer");
    assertNull(calculatorNull);
    assertNotNull(calculator);

    Calculator calculator1 = new Calculator();
    Calculator calculator2 = new Calculator();
    Calculator calculator3 = calculator1;

    assertSame(calculator1, calculator3);
    // assertSame(calculator1, calculator2);

    assertNotSame(calculator1, calculator2);
    //assertNotSame(calculator1, calculator3);

    assertEquals("alberto", "alberto");
    // assertEquals("alberto", "albert", "Ha fallado nuestro metodo String");

    assertEquals(1, 1.4, 0.5);
    //assertEquals(1, 1.6, 0.5);
}
@Test
public void add_ValidInput_ValidExpected_Test(){
        assertEquals(11,calculator.add(7,4));

    System.out.println("@Test -> add_ValidInput_ValidExpected_Test()");
}
@Test
public void subtract_ValidInput_ValidExpected_Test(){
        assertEquals(12, calculator.subtract(12,0));
    }
    @Test
    public void divide_ValidInput_ValidExpected_Test(){
        assertEquals(4, calculator.divide(12,3));
    }
@Test
    public void divide_byZero_ValidInput_ValidExpected_Test(){
    fail("Fallo detectado manualmente");
        calculator.divide(12,0);

    }
@Test
public void divide_byZero_throwException(){
        assertThrows(ArithmeticException.class, ()->calculator.divideByZero(2,0),"Error al dividir por cero");
    }

}