package no.ntnu.idatg2003.chaosgame.backend;

public class Complex extends Vector2D {
  public Complex(double x0, double x1) {
    super(x0, x1);
  }

  public Complex sqrt() {
    double r = Math.sqrt((x0 + Math.sqrt(x0 * x0 + x1 * x1)) / 2);
    double i = Math.signum(x1) * Math.sqrt((-x0 + Math.sqrt(x0 * x0 + x1 * x1)) / 2);
    return new Complex(r, i);
  }

  public double getReal() {
    return x0;
  }

  public double getImaginary() {
    return x1;
  }

  public Complex subtract(Vector2D v) {
    return new Complex(x0 - v.getX0(), x1 - v.getX1());
  }

  public Complex multiply(int sign) {
    return new Complex(sign * x0, sign * x1);
  }
}
