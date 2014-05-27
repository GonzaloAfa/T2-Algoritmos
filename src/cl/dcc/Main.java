package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<KDPoint> points = new ArrayList<KDPoint>();

        KDTree tree = new KDTree(new MeanKDTree());
        tree.construirKDTree(points, KDTree.horizontal);
	// write your code here
    }
}
