package no.ntnu.idatg2003.chaosgame.backend;

public class JuliaTransform implements Transform2D {
  private final Complex point;
  private final int sign;

  /**
   * Constructor for JuliaTransform
   *
   * @param point the point to transform
   * @param sign the sign of the transformation (1 or -1)
   */
  public JuliaTransform(Complex point, int sign) {
    if (sign != 1 && sign != -1) throw new IllegalArgumentException("Sign must be 1 or -1");

    this.point = point;
    this.sign = sign;
  }

  @Override
  public Vector2D transform(Vector2D v) {
    Complex z = new Complex(v.getX0(), v.getX1());
    Complex result = z.subtract(point)     // subtract provided 'point' which presumably is a Complex constant (c in our fractal formulas)
            .sqrt()                            // take the square root
            .multiply(sign);                   // multiply by 'sign' which presumably is either 1 or -1
    return new Vector2D(result.getReal(), result.getImaginary());
  }
}
