package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 02-06-2014.
 */
public class ConstructionStatistics extends Statistics{

    private List<ConstructionKDTree> construction;

    private double  totalTime;


    @Override
    public void addConstruction(ConstructionKDTree data){

        // Obtenemos valores que ya se han ingresado antes
        List<Long> listTime = listData(data.getSizeArray());

        // Actualizamos valores
        this.repetitions= data.getRepetitions();
        this.totalTime =+data.getTime();


        // Calculamos los datos estadisticos
        this.average = totalTime/repetitions;
        this.error = standardDeviation(listTime, data.getTime());


        // Agregamos los datos estadisticos al Pojo
        data.addAverage(this.average);
        data.addError(this.error);



        construction.add(data);
    }


    public ConstructionStatistics(String name, String partition, String typeSequence) {
        super(name, partition, typeSequence);
    }


    private List<Long> listData(long sizeArray){

        List<Long> listTime = new ArrayList<Long>();

        for (ConstructionKDTree temp : construction) {
            if (temp.getSizeArray() == sizeArray ){
                listTime.add(temp.getTime());
                this.totalTime = temp.getTime();
            }
        }
        return listTime;
    }


}
