package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDTree {

    public static final boolean vertical = false, horizontal = true;

    KDNode root;

    SplitMethod splitMethod;

    public KDTree(SplitMethod splitMethod) {
        this.splitMethod = splitMethod;
    }

    public KDNode construirKDTree(List<KDPoint> P, boolean splitaxis) {

        if (P == null || P.size() == 0) {
            return null;
        }

        // Retornamos una hoja con el punto dado
        if (P.size() == 1) {
            return new KDNode(P.get(0));
        }

        // Necesitamos dividir el conjunto de puntos dado cierto criterio
        double splitPoint = splitMethod.getSplitPoint(P, splitaxis);

        ArrayList[] splittedPoints = split(P, splitPoint, splitaxis);

        root.left = construirKDTree(splittedPoints[0], !splitaxis);
        root.right = construirKDTree(splittedPoints[1], !splitaxis);

        return root;
    }

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

    public KDNode insertar() {
        return root;
    }

    public KDNode getRoot() {
        return root;
    }

    public void setRoot(KDNode root) {
        this.root = root;
    }
}
