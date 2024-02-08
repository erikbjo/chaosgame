package no.ntnu.idatg2003.chaosgame.backend;

public class JuliaTransform implements Transform2D {
    private Complex point;
    private int sign;

    public JuliaTransform(Complex point, int sign) {
        this.point = point;
        this.sign = sign;
    }

    @Override
    public Vector2D transform(Vector2D v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
