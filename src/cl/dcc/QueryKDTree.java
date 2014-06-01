package cl.dcc;

/**
 * Created by Gonzaloafa on 01-06-2014.
 */
public class QueryKDTree {

    private long sizeArray;

    private long timeQuery;
    private long accessDisk;

    public QueryKDTree(long sizeArray){
        this.sizeArray = sizeArray;
    }

    public String getData(){
        String data =
                this.sizeArray+";"+
                this.timeQuery+";"+
                this.accessDisk;

        return data;
    }


    public void setSizeArray(long sizeArray) {
        this.sizeArray = sizeArray;
    }

    public void setTimeQuery(long timeQuery) {
        this.timeQuery = timeQuery;
    }

    public void setAccessDisk(long accessDisk) {
        this.accessDisk = accessDisk;
    }
}

