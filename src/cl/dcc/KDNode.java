package cl.dcc;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDNode {
    KDNode left, right;
    KDPoint point;

    public KDNode(KDPoint point) {
        this.point = point;
    }

    public KDNode(KDNode left, KDNode right) {
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
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
}
