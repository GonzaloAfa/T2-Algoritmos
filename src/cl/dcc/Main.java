package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<KDPoint> P = new ArrayList<KDPoint>();
        for (int i = 0; i < 10; i++) {
            P.add(new KDPoint(Math.random() * Math.sqrt(P.size()), Math.random() * Math.sqrt(P.size())));
        }

        KDTree tree = new KDTree(new MedianKDTree());
        tree.construirKDTree(P, KDAxis.horizontal);

        KDPoint q = new KDPoint(Math.random() * Math.sqrt(P.size()),
                                Math.random() * Math.sqrt(P.size()));

        tree.vecinoMasCercano(q);

        int a = 3;
    }
}
