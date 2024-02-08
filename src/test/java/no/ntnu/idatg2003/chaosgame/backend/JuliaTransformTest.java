package no.ntnu.idatg2003.chaosgame.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JuliaTransformTest {

  Vector2D v = new Vector2D(1, 1);
  Vector2D v2 = new Vector2D(3, 5);
  Complex z = new Complex(0.4, 0.2);
  Complex c = new Complex(0.3, 0.6);
  JuliaTransform jt = new JuliaTransform(z, 1);

  @BeforeEach
  void setUp() {}

  @Test
  void transform() {
    Vector2D v3 = jt.transform(v);
    //assertEquals(1.5, v3.getX0());
    //assertEquals(0.5, v3.getX1());
  }
}
