package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class MeanKDTree extends SplitMethod{
    public ArrayList[] split(List<KDPoint> p, double splitPoint, boolean splitaxis) {

        ArrayList[] lists = {new ArrayList<KDPoint>(), new ArrayList<KDPoint>()};

        for (int i = 0; i < p.size(); i++) {
            KDPoint point = p.get(i);
            if (point.getCoord(splitaxis) < splitPoint)
                lists[0].add(point);
            else
                lists[1].add(point);
        }

        return lists;
    }
}
