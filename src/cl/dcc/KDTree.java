package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDTree {

    KDNode root;
    Search search = new Search();

    public static SplitMethod splitMethod;

    public KDTree(SplitMethod splitMethod) {
        this.splitMethod = splitMethod;
    }

    public KDNode construirKDTree(List<KDPoint> P, boolean splitaxis) {

        if (P == null || P.size() == 0) {
            return null;
        }

        root = new KDNode(P,splitaxis);

        return root;
    }

    public KDPoint vecinoMasCercano(KDPoint q) {
        return search.vecinoMasCercano(root, q);
    }

    public static ArrayList[] split(List<KDPoint> p, double splitPoint, boolean splitaxis) {

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

    public KDNode insertar() {
        return root;
    }

    public boolean isCloseEnough() {
        return false;
    }

    public static void main(String[] args){
        List<KDPoint> P = new ArrayList<KDPoint>();
        for (int i = 0; i < 10; i++) {
            P.add(new KDPoint(Math.random(),Math.random()));
        }

        KDTree tree = new KDTree(new MedianKDTree());
        tree.construirKDTree(P, KDAxis.horizontal);

        int a=3;
    }
}
