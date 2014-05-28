package cl.dcc;

/**
 * Created by Ian on 27-05-2014.
 */
public class Search {

    static double distActual;
    static KDPoint mejorActual;

    public KDPoint vecinoMasCercano(KDTree tree, KDPoint q) {
        return vecinoMasCercano(tree.root, q);
    }

    public KDPoint vecinoMasCercano(KDNode node, KDPoint q) {
        KDTree farNode;

        if (node.isLeaf()) {
            checkIfBest(node.getPoint(), q);
            return mejorActual;
        } else if (node.getAxis() > q.getCoord(node.getSplitaxis())) {
            vecinoMasCercano(node.left, q);
            farNode = node.right;
        } else {
            vecinoMasCercano(node.right, q);
            farNode = node.left;
        }
        if (farNode.isCloseEnough())
            vecinoMasCercano(farNode, q);

        return mejorActual;
    }

    public boolean checkIfBest(KDPoint point, KDPoint q) {
        double dist = point.distTo(q);
        if (dist < distActual) {
            mejorActual = point;
            distActual = dist;
            return true;
        }
        return false;
    }

}
