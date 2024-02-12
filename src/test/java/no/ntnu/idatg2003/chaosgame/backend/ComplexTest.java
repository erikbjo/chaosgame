package no.ntnu.idatg2003.chaosgame.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ComplexTest {
  
    @Test
    void sqrtTest() {
        Complex complex = new Complex(3, 4);
        Complex result = complex.sqrt();
        Assertions.assertEquals(2, result.getReal(), 0.000001);
        Assertions.assertEquals(1, result.getImaginary(), 0.000001);
    }

    @Test
    void getRealAndImaginaryTest() {
        Complex complex = new Complex(3, 4);
        Assertions.assertEquals(3, complex.getReal());
        Assertions.assertEquals(4, complex.getImaginary());
    }

    @Test
    void subtractTest() {
        Complex complex1 = new Complex(3, 4);
        Vector2D vector2D = new Vector2D(1, 2);
        Complex result = complex1.subtract(vector2D);
        Assertions.assertEquals(2, result.getReal());
        Assertions.assertEquals(2, result.getImaginary());
    }

    @Test
    void multiplyTest() {
        Complex complex = new Complex(3, 4);
        Complex result = complex.multiply(-1);
        Assertions.assertEquals(-3, result.getReal());
        Assertions.assertEquals(-4, result.getImaginary());
    }
}