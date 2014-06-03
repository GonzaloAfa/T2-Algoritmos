package cl.dcc;

import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDPoint {
    double x, y;

    public KDPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getCoord(boolean c) {
        if (c == KDAxis.horizontal)
            return x;
        else
            return y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distTo(KDPoint point) {
        return Math.sqrt(Math.pow(point.getX() - getX(), 2) + Math.pow(point.getY() - getY(), 2));
    }

    public static double[] toCoordArray(List<KDPoint> P, boolean splitaxis) {
        double[] array = new double[P.size()];
        for (int i = 0; i < P.size(); i++) {
            array[i] = P.get(i).getCoord(splitaxis);
        }
        return array;
    }

    public String toString() {
        return "Point: [" + x + ", " + y + "]";
    }
}
