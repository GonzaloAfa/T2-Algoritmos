package cl.dcc;

import java.util.List;

public class MeanKDTree extends SplitMethod {
    public double getSplitPoint(List<KDPoint> P, boolean splitaxis) {

        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for (int i = P.size(); i > 0; i--) {
            KDPoint point = P.get(i);
            min = Math.min(min, point.getCoord(splitaxis));
            max = Math.max(max, point.getCoord(splitaxis));
        }

        return (min + max) / 2;
    }
}
