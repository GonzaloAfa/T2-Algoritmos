package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 02-06-2014.
 */
public class LowDiscrepancyGenerate extends GeneratePoint {

    public LowDiscrepancyGenerate(double min, double max) {
        super(min, max);
    }

    @Override
    public List<KDPoint> generate(long n){

        List<KDPoint> points = new ArrayList<KDPoint>();

        for (int i = 0; i < n ; i++) {
            points.add(new KDPoint(getHaltonNumber(i, 2), getHaltonNumber(i, 3)));
        }
        return points;
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
