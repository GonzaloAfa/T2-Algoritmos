package cl.dcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */
public class Experiment {


    private String  name;
    private String  typeSequence;

    private List<QueryKDTree> query;

    private int     repetitions;
    private double  error;
    private double  average;
    private double  totalTime;

    public Experiment(String name, String typeSequence) {

        this.name           = name;
        this.typeSequence   = typeSequence;

        this.repetitions    = 0;
        this.average        = 0;
        this.totalTime      = 0;

        this.query          = new ArrayList<QueryKDTree>();
    }


    public void addQuery (QueryKDTree data){

        // Obtenemos valores que ya se han ingresado antes
        List<Long> listTime = data(data.getSizeArray());

        // Actualizamos valores
        this.repetitions = data.getRepetitions();
        this.totalTime=+data.getTime();

        // Calculamos los datos estadisticos
        this.average = totalTime/repetitions;
        this.error = standardDeviation(listTime, data.getTime());

        // Agregamos los datos estadisticos al Pojo
        data.addAverage(this.average);
        data.addError(this.error);

        this.query.add(data);
    }


    public boolean isLowError(){
        return  (this.error < 0.05) ? true : false;
    }



    private double standardDeviation(List<Long> listTime, long lastTime) {

        double sum = 0;

        for (long time : listTime){
            sum +=Math.pow(time - average , 2);
        }
        sum+= Math.pow(lastTime - average, 2);

        return Math.sqrt(sum / repetitions - 1);
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


}
