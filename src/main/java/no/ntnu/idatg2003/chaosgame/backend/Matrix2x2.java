package no.ntnu.idatg2003.chaosgame.backend;

import java.util.Vector;

/**
 * A class representing a 2x2 matrix
 */
public class Matrix2x2 {

    private double a00;
    private double a01;
    private double a10;
    private double a11;


    /**
     * Constructor for the Matrix2x2 class
     * @param a00
     * @param a01
     * @param a10
     * @param a11
     */
    public Matrix2x2(double a00, double a01, double a10, double a11) {
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }


    /**
     * Multiplies the matrix with a vector
     * @param v
     * @return the resulting vector
     */
    public Vector2D multiply(Vector2D v) {
        double x = a00 * v.getX0() + a01 * v.getX1();
        double y = a10 * v.getX0() + a11 * v.getX1();
        return new Vector2D(x, y);
    }
}
