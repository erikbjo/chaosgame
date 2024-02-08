package no.ntnu.idatg2003.chaosgame.backend;

public class AffineTransform2D implements Transform2D {
  private final Matrix2x2 matrix;
  private final Vector2D vector;

  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    this.matrix = matrix;
    this.vector = vector;
  }

  @Override
  public Vector2D transform(Vector2D v) {
    Vector2D ax = matrix.multiply(v);
    return ax.add(vector);
  }
}
