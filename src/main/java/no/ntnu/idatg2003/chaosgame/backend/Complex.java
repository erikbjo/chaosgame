package no.ntnu.idatg2003.chaosgame.backend;

public class Complex extends Vector2D {
    
        public Complex(double x0, double x1) {
            super(x0, x1);
        }

        public Complex addComplex(Complex c, Complex c2) {
            return new Complex(c.getX0() + c2.getX0(), c.getX1() + c2.getX1());
        }

        public double subtractComplex(Complex c) {
            return new Complex(c.getX0() - this.getX0(), c.getX1() - this.getX1()).getLength();
        }

        public double getLength() {
            return Math.sqrt(x0 * x0 + x1 * x1);
        }
}
