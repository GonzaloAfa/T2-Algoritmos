package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */
abstract class GeneratePoint {

    protected double min;
    protected double max;

    public GeneratePoint(double min, double max){

        if (min < max) {
            this.min = min;
            this.max = max;
        }else{
            this.min = max;
            this.max = min;
        }
    }

    public abstract List<KDPoint> generate(long n);



}