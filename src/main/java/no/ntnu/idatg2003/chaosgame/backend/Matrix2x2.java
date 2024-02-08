package no.ntnu.idatg2003.chaosgame.backend;

public class Matrix2x2 {

    private double a00;
    private double a01;
    private double a10;
    private double a11;



    public Matrix2x2(double a00, double a01, double a10, double a11) {
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }



    public Vector2D multiply(Vector2D v) {
        double x = a00 * v.getX() + a01 * v.getY();
        double y = a10 * v.getX() + a11 * v.getY();
        return new Vector2D(x, y);
    }
}
