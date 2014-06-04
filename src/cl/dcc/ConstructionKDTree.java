package cl.dcc;

/**
 * Created by Gonzaloafa on 01-06-2014.
 */
public class ConstructionKDTree {

    private long    sizeArray;

    private long    repetitions;

    private long    time;
    private long    height;
    private double  spaceDisk;
    private long    accessDisk;


    private double  error;

    private double  averageTime;
    private double  averageHeight;
    private double  averageSpaceDisk;
    private double  averageAccessDisk;


    public ConstructionKDTree(long sizeArray){
        this.sizeArray = sizeArray;
    }

    public String getHeader(){
        String data =   "sizeArray;"+
                        "repetitions;"+
                        "time;"+
                        "error;"+
                        "height;"+
                        "spaceDisk;"+
                        "accessDisk;"+
                        "averageTime;"+
                        "averageSpaceDisk;"+
                        "averageAccessDisk;";

        return data;
    }

    public String getData(){

        String data =
                this.sizeArray +";"+
                this.repetitions+";"+
                this.time+";"+
                this.error+";"+
                this.height+";"+
                this.spaceDisk+";"+
                this.accessDisk+";"+
                this.averageTime+";"+
                this.averageHeight+";"+
                this.averageSpaceDisk+";"+
                this.averageAccessDisk;
        return data;
    }



    public long getTime() {
        return time;
    }

    public void addTime(long time) {
        this.time = time;
    }

    public void addHeight(long height) {
        this.height = height;
    }

    public void addSpaceDisk(double spaceDisk) {
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

    public double getAverageTime() {
        return averageTime;
    }

    public void addAverageTime(double averageTime) {
        this.averageTime = averageTime;
    }

    public double getHeight() {
        return height;
    }

    public void addAverageHeight(double averageHeight) {
        this.averageHeight = averageHeight;
    }

    public double getSpaceDisk() {
        return spaceDisk;
    }

    public void addAverageSpaceDisk(double averageSpaceDisk) {
        this.averageSpaceDisk = averageSpaceDisk;
    }

    public double getAccessDisk() {
        return accessDisk;
    }

    public void addAverageAccessDisk(double averageAccessDisk) {
        this.averageAccessDisk = averageAccessDisk;
    }

    public long getSizeArray(){
        return sizeArray;
    }
}
