package cl.dcc;

/**
 * Created by Ian on 29-05-2014.
 */
public class KDRect {
    private double xi, yi, xf, yf;

    /**
     * yf    .  -  -  -  -  - .
     * |        A       |
     * |B              C|
     * |        D       |
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
        rects[1] = new KDRect(xi, yi, xf, yf);

        if (axis.getSplitaxis() == KDAxis.horizontal) {
            // Dividimos horizontalmente
            rects[0].setMaxX(axis.getValue());
            rects[1].setMinX(axis.getValue());
        } else {
            // Dividimos verticalmente. Left corresponde al sector de abajo
            rects[0].setMaxY(axis.getValue());
            rects[1].setMinY(axis.getValue());

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

    public boolean intersects(KDPoint q, double distActual) {
        return isInside(q, distActual) || intersectHorizontal(q, distActual, true) || intersectHorizontal(q, distActual, false) ||
                intersectVertical(q, distActual, true) || intersectVertical(q, distActual, false);
    }

    private boolean isInside(KDPoint q, double distActual) {
        return q.getX()>=xi && q.getX()<=xf && q.getY()>=yi && q.getY()<=yf;
    }

    private boolean intersectHorizontal(KDPoint q, double distActual, boolean upper) {
        return q.getX() >= xi && q.getX() <= xf && Math.abs(q.getY() - (upper ? yf : yi)) <= distActual;
    }

    private boolean intersectVertical(KDPoint q, double distActual, boolean left) {
        return q.getY() >= yi && q.getY() <= yf && Math.abs(q.getX() - (left ? xi : xf)) <= distActual;
    }
}
