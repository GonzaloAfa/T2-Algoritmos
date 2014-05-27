package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class MeanKDTree extends SplitMethod{
    public double getSplitPoint(List<KDPoint> P, boolean splitaxis) {

        double mean = 0;
        for (int i = P.size(); i > 0; i--) {
            KDPoint point = P.get(i);
            mean += point.getCoord(splitaxis);
        }

        return mean /= P.size();
    }
}
