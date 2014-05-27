package cl.dcc;

import java.util.*;

public class MedianKDTree extends SplitMethod{
    public double getSplitPoint(List<KDPoint> P, boolean splitaxis){

        ArrayList[] lists = {new ArrayList<KDPoint>(), new ArrayList<KDPoint>()};

        Collections.sort(P,new KDPointComparator(splitaxis));

        return P.get((int)Math.round(P.size()/2)).getCoord(splitaxis);
    }
}
