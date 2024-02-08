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


    public double getX1() {
        return x1;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public Vector2D addVector(Vector2D v, Vector2D v2) {
        return new Vector2D(v.getX0() + v2.getX0(), v.getX1() + v2.getX1());
    }

    public double subtractVector(Vector2D v) {
        return new Vector2D(v.getX0() - this.getX0(), v.getX1() - this.getX1()).getLength();
    }

    public double getLength() {
        return Math.sqrt(x0 * x0 + x1 * x1);
    }
}
