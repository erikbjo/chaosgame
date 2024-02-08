package no.ntnu.idatg2003.chaosgame.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AffineTransform2DTest {

  private Matrix2x2 matrix;
  private Vector2D b;
  private Vector2D x;

  @BeforeEach
  void setUp() {
    matrix = new Matrix2x2(0.5, 1, 1, 0.5);
    b = new Vector2D(3, 1);
    x = new Vector2D(1, 2);
  }

  @Test
  void transform() {
    AffineTransform2D at = new AffineTransform2D(matrix, b);
    Vector2D v = at.transform(x);
    assertEquals(5.5, v.getX0());
    assertEquals(3, v.getX1());
  }
}
