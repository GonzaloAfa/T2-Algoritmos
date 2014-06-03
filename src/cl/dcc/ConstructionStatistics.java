package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 02-06-2014.
 */
public class ConstructionStatistics extends Statistics{

    private List<ConstructionKDTree> construction;

    private double totalTime;
    private double totalHeight;
    private double totalSpaceDisk;
    private double totalAccessDisk;


    public ConstructionStatistics(String name, String partition, String typeSequence) {
        super(name, partition, typeSequence);

        construction = new ArrayList<ConstructionKDTree>();

        totalAccessDisk = 0;
        totalHeight     = 0;
        totalSpaceDisk  = 0;
        totalTime       = 0;
    }


    @Override
    public void addConstruction(ConstructionKDTree data){

        // Obtenemos valores que ya se han ingresado antes
        List<Long> listTime = listData(data.getSizeArray());

        // Actualizamos valores
        this.repetitions    = data.getRepetitions();
        this.error          = standardDeviation(listTime, data.getTime());


        // Agregamos los datos estadisticos al Pojo

        data.addAverageTime((totalTime+data.getTime())/repetitions);
        data.addAverageHeight((totalHeight+data.getHeight())/repetitions);
        data.addAverageSpaceDisk((totalSpaceDisk+data.getSpaceDisk())/repetitions);
        data.addAverageAccessDisk((totalAccessDisk+data.getAccessDisk())/repetitions);

        data.addError(this.error);
        construction.add(data);
    }




    private List<Long> listData(long sizeArray){

        List<Long> listTime = new ArrayList<Long>();

        for (ConstructionKDTree temp : construction) {
            if (temp.getSizeArray() == sizeArray ){

                listTime.add(temp.getTime());

                // Obtenemos el total de los datos
                this.totalTime =+ temp.getTime();
                this.totalHeight =+ temp.getHeight();
                this.totalSpaceDisk =+ temp.getSpaceDisk();
                this.totalAccessDisk =+ temp.getAccessDisk();
            }
        }
        return listTime;
    }



    public String getReport(){
        String result = "";

        result = construction.get(0).getHeader()+"\n";
        for (ConstructionKDTree temp: construction){
            result = result + temp.getData()+"\n";
        }

        return result;
    }

    public String getSummary() {
        String summary = "";
        // TODO crear resumen
        return summary;
    }

}
