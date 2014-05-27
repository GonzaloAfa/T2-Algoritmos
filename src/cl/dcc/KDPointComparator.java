package cl.dcc;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDPointComparator implements java.util.Comparator<KDPoint> {
    boolean splitaxis;

    public KDPointComparator(boolean splitaxis) {
        this.splitaxis = splitaxis;
    }

    @Override
    public int compare(KDPoint kdPoint1, KDPoint kdPoint2) {
        double p1 = kdPoint1.getCoord(splitaxis);
        double p2 = kdPoint2.getCoord(splitaxis);
        if (p1 == p2)
            return 0;
        else if (p1 >= p2)
            return 1;
        else
            return -1;
    }

}
