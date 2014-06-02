package cl.dcc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 *
 *
 * --------------------------------------------------------------------------------------------------------------------------
 * | myFilePos   | left  | right | axis  | point | rect  |
 * --------------------------------------------------------------------------------------------------------------------------
 * | B           | B     | B     | B     | B     | B     |

 */


public class KDNode {

    private final static int BOOLEAN = 1;
    private final static int INT     = 4;
    private final static int FLOAT   = 4;
    private final static int DOUBLE  = 8;
    private final static int LONG    = 8;

    // 2 Punteros a izquierdo y derecho, 1 booleano y un double para el eje, 2 double para el punto,
    // 4 double para el rectangulo y 2 enteros para el número de nodos
    private final static double nodeSize = DOUBLE*2 + BOOLEAN + DOUBLE + DOUBLE*2 + DOUBLE*4
            + INT + DOUBLE;

    // FilePosicion
    private long myFilePos;

    // Internal node data
    private KDNode left, right;
    private KDAxis axis;

    // Leaf data
    private KDPoint point;

    // Bounding rectangle
    protected KDRect rect;

    private int height, nodeCount;

    public KDNode(KDAxis kdAxis) {
        axis = kdAxis;
    }

    public KDNode(List<KDPoint> P, boolean splitaxis, KDRect bounds) {
        // Retornamos una hoja con el punto dado
        if (P.size() == 1) {
            point = P.get(0);
            height = 1;
            nodeCount = 1;
            return;
        }

        // Marcamos los límites de este nodo interno
        rect = bounds;

        // Necesitamos dividir el conjunto de puntos dado cierto criterio
        double splitPoint = KDTree.splitMethod.getSplitPoint(P, splitaxis);

        axis = new KDAxis(splitPoint, splitaxis);

        ArrayList[] splittedPoints = KDTree.split(P, splitPoint, splitaxis);

        KDRect[] boundingRects = rect.split(axis);

        setLeft(new KDNode(splittedPoints[0], !splitaxis, boundingRects[0]));
        setRight(new KDNode(splittedPoints[1], !splitaxis, boundingRects[1]));

        height = 1 + Math.max(left.getHeight(), right.getHeight());
        nodeCount = 1 + left.getNodeCount() + right.getNodeCount();
    }

    public KDNode(byte [] buffer) {
       // TODO: Completar el KDNode

        int ini= 0;

        double x = ByteBuffer.wrap(buffer, ini, DOUBLE).getDouble();
        ini=+DOUBLE;

        double y = ByteBuffer.wrap(buffer, ini, DOUBLE).getDouble();
        ini=+DOUBLE;

        point.setX(x);
        point.setY(y);

        // TODO KDNode Left

        // TODO KDNode Right

        // TODO KDAxis axis

    }


    public KDNode(KDPoint kdPoint) {
        point = kdPoint;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isCloseEnough(KDPoint q, double distActual) {
        return getRect().intersects(q, distActual);
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

    public String toString() {
        if (isLeaf())
            return "Leaf: " + point;
        return "Node: " + axis + "\n\t" + left + "\n\t" + right;
    }

    public KDRect getRect() {
        return rect;
    }

    public long getMyFilePos() {
        return myFilePos;
    }

    public void setMyFilePos(long myFilePos) {
        this.myFilePos = myFilePos;
    }

    public KDRect getKDRect(){
        return rect;
    }

    public void writeToBuffer(byte [] buffer) throws IOException {
        // TODO: Terminar esto

        int ini= 0;

        // KDPoint
        ByteBuffer.wrap(buffer, ini, DOUBLE).putDouble(point.getX());
        ini= ini+DOUBLE; //dejo el puntero al final de getX

        ByteBuffer.wrap(buffer, ini, DOUBLE).putDouble(point.getY());
        ini= ini+DOUBLE; //dejo el puntero al final de getY



        // TODO KDNode Left
//        ByteBuffer.wrap(buffer, ini, FLOAT).putLong(left);
//        ini= ini+FLOAT; //dejo el puntero al final de Left

        // TODO KDNode Right
//        ByteBuffer.wrap(buffer, ini, FLOAT).putLong(right);
//        ini= ini+FLOAT; //dejo el puntero al final de Right



        // TODO KDAxis axis
//        ByteBuffer.wrap(buffer, ini, FLOAT).putLong(right);
//        ini= ini+FLOAT; //dejo el puntero al axis


        // MyFilePos
        ByteBuffer.wrap(buffer, ini, FLOAT).putFloat(myFilePos);
        ini= ini+FLOAT; //dejo el puntero al final del campo myFilePos

    }

    public int getHeight() {
        return height;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public static double getNodeSize() {
        return nodeSize;
    }
}
