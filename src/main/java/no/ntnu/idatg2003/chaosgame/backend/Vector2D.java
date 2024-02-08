package no.ntnu.idatg2003.chaosgame.backend;

public class Vector2D {

    protected double x0;
    protected double x1;

    public Vector2D(double x0, double x1) {
        this.setX0(x0);
        this.setX1(x1);
    }


    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public Vector2D subtract(Vector2D v) {
        return new Vector2D(x0 - v.getX0(), x1 - v.getX1());
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x0 + other.getX0(), x1 + other.getX1());
    }

    public double getLength() {
        return Math.sqrt(x0 * x0 + x1 * x1);
    }
}
