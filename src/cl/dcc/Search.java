package cl.dcc;

/**
 * Created by Ian on 27-05-2014.
 */
public class Search {

    static double distActual;
    static KDPoint mejorActual;

    public Search(){
        distActual = Double.MAX_VALUE;
        mejorActual = null;
    }

    public KDPoint vecinoMasCercano(KDNode node, KDPoint q) {
        KDNode farNode = null;

        if (node.isLeaf()) {
            checkIfBest(node.getPoint(), q);
            return mejorActual;
        } else if (node.greaterThanAxis(q)) {
            vecinoMasCercano(node.getRight(), q);
            farNode = node.getLeft();
        } else {
            vecinoMasCercano(node.getLeft(), q);
            farNode = node.getRight();
        }
        if (farNode.isCloseEnough(q,distActual))
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
