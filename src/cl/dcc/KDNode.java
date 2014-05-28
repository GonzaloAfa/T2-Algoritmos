package cl.dcc;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDNode {

    double axis;
    KDNode left;
    KDNode right;
    KDPoint point;
    boolean splitaxis, root;

    public KDNode(KDPoint point) {
        this.point = point;
    }

    public KDNode(KDNode left, KDNode right) {
        this.left = left;
        this.right = right;
    }

    public KDNode(KDPoint point, KDNode left, KDNode right, boolean splitaxis) {
        this.left = left;
        this.right = right;
        this.point = point;
        this.splitaxis = splitaxis;
    }

    public KDNode(double axis, boolean splitaxis, KDNode left, KDNode right) {
        this.left = left;
        this.right = right;
        this.axis = axis;
        this.splitaxis = splitaxis;
    }

    public KDNode() {
        root = true;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public double getAxis() {
        return axis;
    }

    public void setAxis(double axis) {
        this.axis = axis;
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public KDPoint getPoint() {
        return point;
    }

    public void setPoint(KDPoint point) {
        this.point = point;
    }

    public boolean getSplitaxis() {
        return splitaxis;
    }

    public void setSplitaxis(boolean splitaxis) {
        this.splitaxis = splitaxis;
    }

    public void setSplitPoint(double splitPoint, boolean splitaxis) {
        setSplitaxis(splitaxis);
        setAxis(axis);
    }
}
