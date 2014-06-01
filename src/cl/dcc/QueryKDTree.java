package cl.dcc;

/**
 * Created by Gonzaloafa on 01-06-2014.
 */
public class QueryKDTree {

    private long sizeArray;

    private long timeQuery;
    private long accessDisk;


    private int     repetitions;
    private double  error;
    private double  average;
    private double  averageAccess;


    public QueryKDTree(long sizeArray){

        this.sizeArray      = sizeArray;
        this.timeQuery      = 0;
        this.accessDisk     = 0;
        this.repetitions    = 0;
        this.average        = 0;
        this.averageAccess  = 0;
        this.error          = 0;
    }

    public String getHeader(){
        String data =
                "Tamano;" +
                "Tiempo;"+
                "Accesos;"+
                "Repeticiones;"+
                "PromedioTiempo;"+
                "PromedioAcceso;"+
                "Error;";
        return data;
    }

    public String getData(){
        String data =
                this.sizeArray+";"+
                this.timeQuery+";"+
                this.accessDisk+";"+
                this.repetitions+";"+
                this.average+";"+
                this.averageAccess+";"+
                this.error+";";
        return data;
    }


    public void addSizeArray(long sizeArray) {
        this.sizeArray = sizeArray;
    }

    public void addTimeQuery(long timeQuery) {
        this.timeQuery = timeQuery;
    }

    public void addAccessDisk(long accessDisk) {
        this.accessDisk = accessDisk;
    }

    public void addAverage(double average) {
        this.average = average;
    }

    public void addAverageAccess(double averageAccess) {
        this.averageAccess = averageAccess;
    }

    public void addRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public double getError() {
        return error;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void addError(double error) {
        this.error = error;
    }

    public long getTime(){
        return timeQuery;
    }

    public double getAverage() {
        return average;
    }


    public long getSizeArray(){
        return sizeArray;
    }

}

