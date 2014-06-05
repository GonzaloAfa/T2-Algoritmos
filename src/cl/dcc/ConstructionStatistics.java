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

        construction    = new ArrayList<ConstructionKDTree>();

        totalAccessDisk = 0;
        totalHeight     = 0;
        totalSpaceDisk  = 0;
        totalTime       = 0;
    }


    @Override
    public void addConstruction(ConstructionKDTree data){

        // Obtenemos valores que ya se han ingresado antes
        List<Long> listTime = new ArrayList<Long>();

        if ( data.getRepetitions() == 0)
            listTime.add(data.getTime());
        else
            listTime = listData();

        // Actualizamos valores
        repetitions         = data.getRepetitions()+1;

        // Agregamos los datos estadisticos al Pojo
        totalTime           = totalTime         + data.getTime();
        totalHeight         = totalHeight       + data.getHeight();
        totalSpaceDisk      = totalSpaceDisk    + data.getSpaceDisk();
        totalAccessDisk     = totalAccessDisk   + data.getAccessDisk();


        data.addRepetitions(repetitions);
        data.addAverageTime(totalTime/repetitions);
        data.addAverageHeight(totalHeight/repetitions);
        data.addAverageSpaceDisk(totalSpaceDisk/repetitions);
        data.addAverageAccessDisk(totalAccessDisk/repetitions);

        this.averageTime    = totalTime/repetitions;
        this.error          = standardDeviation(listTime, data.getTime());

        // entrega el porcentaje de error
        data.addError(this.error * 100 / data.getAverageTime());

        construction.add(data);
    }

    @Override
    public String getHeader() {
        return construction.get(0).getHeader();
    }


    private List<Long> listData(){

        List<Long> listTime = new ArrayList<Long>();

        for (ConstructionKDTree temp : construction) {
                listTime.add(temp.getTime());
        }
        return listTime;
    }


    @Override
    public String getReport(){
        String result = "";

        for (ConstructionKDTree temp: construction)
            result = result + temp.getData()+"\n";

        return result;
    }

    @Override
    public String getSummary() {
        String summary = construction.get(construction.size()-1).getData()+"\n";
        System.out.print(summary);

        return summary;
    }

    @Override
    public void clean(){
        construction.clear();

        totalTime       = 0;
        totalHeight     = 0;
        totalSpaceDisk  = 0;
        totalAccessDisk = 0;

    }

}