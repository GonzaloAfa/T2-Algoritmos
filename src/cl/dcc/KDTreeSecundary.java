package cl.dcc;

import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 27-05-2014.
 */
public class KDTreeSecundary {

    KDNode root;
    Search search;

    protected MemoryManager mem;


    public static SplitMethod splitMethod;
    public long getHeight;

    public KDTreeSecundary (SplitMethod splitMethod) {
        this.splitMethod = splitMethod;
    }

    public KDNode construirKDTree(List<KDPoint> P, boolean splitaxis) throws IOException {

        if (P == null || P.size() == 0) {
            return null;
        }

        int bufSize = 85;
        int k = (int) Math.ceil(((double)bufSize)/((double)4096));
        mem = new MemoryManager(10, 4096*k);

        KDRect rect = new KDRect(0, 0, Math.sqrt(P.size()), Math.sqrt(P.size()));
        root = new KDNode(P, splitaxis, rect, mem);
        root.getHeight();

        mem.saveNode(root);
        return root;
    }

    public KDPoint vecinoMasCercano(KDPoint q) {
        search = new Search();
        return search.vecinoMasCercano(root, q);
    }

    public double getUsedSpace(){
        return root.getNodeCount()*root.getNodeSize();
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

    public static void main(String[] args) {
        List<KDPoint> P = new ArrayList<KDPoint>();
        for (int i = 0; i < 10; i++) {
            P.add(new KDPoint(Math.random() * Math.sqrt(P.size()), Math.random() * Math.sqrt(P.size())));
        }

        KDTree tree = new KDTree(new MedianKDTree());
        tree.construirKDTree(P, KDAxis.horizontal);

        int a = 3;
    }
}
