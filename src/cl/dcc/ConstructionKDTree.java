package cl.dcc;

/**
 * Created by Gonzaloafa on 01-06-2014.
 */
public class ConstructionKDTree {

    private long    sizeArray;

    private long    timeConstruction;
    private long    height;
    private long    spaceDisk;
    private long    accessDisk;

    private long    repetitions;
    private double  error;
    private double  average;
    private double  averageAccess;
    private long time;


    public ConstructionKDTree(long sizeArray){
        this.sizeArray = sizeArray;
    }

    public String getHeader(){
        // TODO:
        return "";
    }

    public String getData(){

        String data =
                this.sizeArray +";"+
                this.timeConstruction+";"+
                this.height+";"+
                this.spaceDisk+";"+
                this.accessDisk;
        return data;
    }



    public void addTimeConstruction(long timeConstruction) {
        this.timeConstruction = timeConstruction;
    }

    public void addHeight(long height) {
        this.height = height;
    }

    public void addSpaceDisk(long spaceDisk) {
        this.spaceDisk = spaceDisk;
    }

    public void addAccessDisk(long accessDisk) {
        this.accessDisk = accessDisk;
    }

    public long getRepetitions() {
        return repetitions;
    }

    public void addRepetitions(long repetitions) {
        this.repetitions = repetitions;
    }

    public double getError() {
        return error;
    }

    public void addError(double error) {
        this.error = error;
    }

    public double getAverage() {
        return average;
    }

    public void addAverage(double average) {
        this.average = average;
    }

    public double getAverageAccess() {
        return averageAccess;
    }

    public void addAverageAccess(double averageAccess) {
        this.averageAccess = averageAccess;
    }

    public long getSizeArray() {
        return sizeArray;
    }

    public long getTime() {
        return time;
    }
}
