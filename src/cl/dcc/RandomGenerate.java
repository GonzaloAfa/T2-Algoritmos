package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 02-06-2014.
 */
public class RandomGenerate extends GeneratePoint {

    public RandomGenerate(double min, double max) {
        super(min, max);
        this.min = min;
        this.max = max;
    }


    public List<KDPoint> generate(long n) {

        List<KDPoint> points = new ArrayList<KDPoint>();

        for (int i = 0; i < n; i++) {
            points.add(new KDPoint(random(), random()));
        }

        return points;
    }

    private double random() {
        return this.min + (Math.random() * ((this.max - this.min) + 1));
    }

}


