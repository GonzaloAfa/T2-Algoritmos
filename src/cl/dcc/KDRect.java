package cl.dcc;

/**
 * Created by Ian on 29-05-2014.
 */
public class KDRect {
    private double xi, yi, xf, yf;

    /**
     * yf    .  -  -  -  -  - .
     * |                |
     * |                |
     * |                |
     * yi   |  -  -  -  -  - .
     * xi              xf
     */

    public KDRect(double xi, double yi, double xf, double yf) {
        this.xi = xi;
        this.yi = yi;
        this.xf = xf;
        this.yf = yf;
    }

    public KDRect[] split(KDAxis axis) {
        // Guardamos left en la posición 0
        KDRect[] rects = new KDRect[2];

        rects[0] = new KDRect(xi, yi, xf, yf);
        rects[1] = new KDRect(xi, yi, yf, yf);

        if (axis.getSplitaxis() == KDAxis.horizontal) {
            // Dividimos horizontalmente
            rects[0].setMaxX(axis.getValue());
            rects[1].setMinX(axis.getValue());
        } else {
            // Dividimos verticalmente. Left corresponde al sector de arriba
            rects[0].setMinY(axis.getValue());
            rects[1].setMaxY(axis.getValue());
        }

        return rects;
    }

    public double getMinX() {
        return xi;
    }

    public double getMinY() {
        return yi;
    }

    public double getMaxY() {
        return yf;
    }

    public double getMaxX() {
        return xf;
    }

    public void setMaxX(double maxX) {
        this.xf = maxX;
    }

    public void setMinX(double minX) {
        this.xi = minX;
    }

    public void setMinY(double minY) {
        this.yi = minY;
    }

    public void setMaxY(double maxY) {
        this.yf = maxY;
    }

    public String toString() {
        return "Rect: [" + xi + ", " + yi + ", " + xf + ", " + yf + "]";
    }
}
