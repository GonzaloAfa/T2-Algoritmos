package cl.dcc;

/**
 * Created by Ian on 28-05-2014.
 */
public class KDAxis {
    public static final boolean vertical = false;
    public static final boolean horizontal = true;
    double value;
    boolean splitaxis;

    public KDAxis(double value, boolean splitaxis) {
        this.value = value;
        this.splitaxis = splitaxis;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getSplitaxis() {
        return splitaxis;
    }

    public void setSplitaxis(boolean splitaxis) {
        this.splitaxis = splitaxis;
    }
}
