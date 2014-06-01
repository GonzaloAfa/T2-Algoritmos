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


    public ConstructionKDTree(long sizeArray){
        this.sizeArray = sizeArray;
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
}
