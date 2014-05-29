package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDNode {

    // Internal node data
    private KDNode left, right;
    private KDAxis axis;

    // Leaf data
    private KDPoint point;

    public KDNode(KDAxis kdAxis) {
        axis = kdAxis;
    }

    public KDNode(List<KDPoint> P, boolean splitaxis) {
        // Retornamos una hoja con el punto dado
        if (P.size() == 1) {
            point = P.get(0);
            return;
        }

        // Necesitamos dividir el conjunto de puntos dado cierto criterio
        double splitPoint = KDTree.splitMethod.getSplitPoint(P, splitaxis);

        axis = new KDAxis(splitPoint, splitaxis);

        ArrayList[] splittedPoints = KDTree.split(P, splitPoint, splitaxis);

        setLeft(new KDNode(splittedPoints[0], !splitaxis));
        setRight(new KDNode(splittedPoints[1], !splitaxis));
    }

    public KDNode(KDPoint kdPoint) {
        point = kdPoint;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isCloseEnough() {
        return false;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public KDPoint getPoint() {
        return point;
    }

    public boolean greaterThanAxis(KDPoint q) {
        KDAxis axis = getAxis();
        return axis.getValue() < q.getCoord(axis.getSplitaxis());
    }

    public KDAxis getAxis() {
        return axis;
    }

    public KDNode getLeft() {
        return left;
    }

    public KDNode getRight() {
        return right;
    }
}
