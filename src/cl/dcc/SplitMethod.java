package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public abstract class SplitMethod {
    public abstract double getSplitPoint(List<KDPoint> P, boolean splitaxis);
}

