package cl.dcc;

import java.util.List;

/**
 * Created by Gonzaloafa on 03-06-2014.
 */
abstract class Statistics {

    private String name;
    private String partition;
    private String typeSequence;

    protected long repetitions;
    protected double averageTime;


    protected double error;


    public Statistics(String name, String partition, String typeSequence){
        this.name       = name;
        this.partition  = partition;
        this.typeSequence = typeSequence;
    }

    protected double standardDeviation(List<Long> data, long lastData) {
        double sum = 0;
        for (long time : data){
            sum +=Math.pow(time - averageTime, 2);
        }
        sum+= Math.pow(lastData - averageTime, 2);
        return Math.sqrt(sum / repetitions - 1);
    }

    public boolean isLowError(){
        return  (this.error < 5);
    }

    public void addConstruction(ConstructionKDTree data){
    }

    public void addQuery(QueryKDTree data){
    }

    public abstract String getHeader();
    public abstract String getReport();
    public abstract String getSummary();
    public abstract void clean();


    public String getName() {
        return name;
    }

    public String getPartition() {
        return partition;
    }

    public String getTypeSequence() {
        return typeSequence;
    }
}
