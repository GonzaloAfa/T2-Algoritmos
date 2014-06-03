package cl.dcc;

import java.util.List;

/**
 * Created by Gonzaloafa on 02-06-2014.
 */
public class Statistics {

    protected String name;
    protected String typeSequence;
    protected String partition;

    protected long  repetitions;
    protected long  totalTime;
    protected double average;
    protected double error;


    public Statistics(String name, String partition, String typeSequence){
        this.name           = name;
        this.partition      = partition;
        this.typeSequence   = typeSequence;

        this.repetitions    = 0;
        this.average        = 0;
        this.totalTime      = 0;

    }

    public String getName() {
        return name;
    }

    public String getPartition() {
        return typeSequence;
    }

    public String getTypeSequence() {
        return typeSequence;
    }

    public void addConstruction(ConstructionKDTree data){
    }

    public void addQuery(QueryKDTree data){
    }



    public boolean isLowError(){
        return  (this.error < 0.05) ? true : false;
    }


    protected double standardDeviation(List<Long> data, long lastData) {

        double sum = 0;
        for (long time : data){
            sum +=Math.pow(time - average , 2);
        }
        sum+= Math.pow(lastData - average, 2);
        return Math.sqrt(sum / repetitions - 1);
    }



}
