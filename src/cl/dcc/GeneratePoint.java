package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */
public class GeneratePoint {

    private int min;
    private int max;

    public GeneratePoint(int min, int max){
        if (min < max) {
            this.min = min;
            this.max = max;
        }else{
            this.min = max;
            this.max = min;
        }
    }

    public List<KDPoint> random(int n){

        List<KDPoint> points = new ArrayList<KDPoint>();

        for (int i = 0; i < n ; i++) {
            points.add(new KDPoint(random(), random()));
        }

        return points;
    }

    public List<KDPoint> lowDiscrepancy(int n){

        List<KDPoint> points = new ArrayList<KDPoint>();

        for (int i = 0; i < n ; i++) {
            points.add(new KDPoint(getHaltonNumber(i, 2), getHaltonNumber(i, 3)));
        }
        return points;
    }


    private double random(){
        return min + (Math.random() * ((max - min) + 1));
    }


    // TODO about Halton Sequence > http://en.wikipedia.org/wiki/Halton_sequence

    /**
     * Get Halton number for given index and base.
     *
     * @param index Index of the Halton number (starting at 0).
     * @param base Base of the Halton number.
     * @return Halton number.
     */

    public double getHaltonNumber(int index, int base) {

        // Check base
        if(base < 2)
            base = 2;
        if(index < 0)
            index = 0;

        // Index shift: counting of the function starts at 0, algorithm below starts at 1.
        index++;

        // Calculate Halton number x
        double x = 0;

        double factor = 1.0/base;

        while(index > 0) {
            x += (index % base) * factor;
            factor /= base;
            index /= base;
        }
        return (min + x * ((max - min) + 1));
    }
}