package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */
public class QueryStatistics extends Statistics{


    private List<QueryKDTree> query;

    private double  totalTime;

    public QueryStatistics(String name, String partition, String typeSequence) {
        super(name,partition,typeSequence);
        this.query          = new ArrayList<QueryKDTree>();
    }


    public void addQuery (QueryKDTree data){

        // Obtenemos valores que ya se han ingresado antes
        List<Long> listTime = data(data.getSizeArray());

        // Actualizamos valores
        this.repetitions= data.getRepetitions();
        this.totalTime =+data.getTime();


        // Calculamos los datos estadisticos
        this.average = totalTime/repetitions;
        this.error = standardDeviation(listTime, data.getTime());


        // Agregamos los datos estadisticos al Pojo
        data.addAverage(this.average);
        data.addError(this.error);

        this.query.add(data);
    }


    private List<Long> data(long sizeArray){

        List<Long> listTime = new ArrayList<Long>();

        for (QueryKDTree temp : query) {
            if (temp.getSizeArray() == sizeArray ){
                listTime.add(temp.getTime());
                this.totalTime = temp.getTime();
            }
        }
        return listTime;
    }



    public String getReport(){
        String result = "";

        result = query.get(0).getHeader()+"\n";
        for (QueryKDTree temp: query){
            result = result + temp.getData()+"\n";
        }

        return result;
    }
}
