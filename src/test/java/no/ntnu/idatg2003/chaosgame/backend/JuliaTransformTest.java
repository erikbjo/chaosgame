package no.ntnu.idatg2003.chaosgame.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JuliaTransformTest {

  Vector2D v = new Vector2D(1, 1);
  Vector2D v2 = new Vector2D(3, 5);
  Complex z = new Complex(0.4, 0.2);
  Complex c = new Complex(0.3, 0.6);
  JuliaTransform jt = new JuliaTransform(c, 1);

  @BeforeEach
  void setUp() {}

  @Test
  void transform() {
    assertTrue(String.valueOf(jt.transform(z).getX0()).startsWith("0.506"));
    assertTrue(String.valueOf(jt.transform(z).getX1()).startsWith("-0.395"));
  }
}
