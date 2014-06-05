package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Experimento en memoria principal
        int minSizeArray    = 10;
        int maxSizeArray    = 15;

        // Size Array
        long sizeArray[] = new long[maxSizeArray - minSizeArray + 1];

        // Init Array
        for (int i = 0; i < sizeArray.length; i++)
            sizeArray[i] = (long) Math.pow(2.0, (double) (minSizeArray + i));



        Statistics statistic[][] = {
              { new ConstructionStatistics("Memoria Principal", " Media",   "Random" ),
                new ConstructionStatistics("Memoria Principal", " Media" ,  "Baja Discrepancia" )}
                ,
              { new ConstructionStatistics("Memoria Principal", " Mediana", "Random"),
                new ConstructionStatistics("Memoria Principal", " Mediana", "Baja Discrepancia")}
        };


        KDTree kdtree []        = {new KDTree(new MeanKDTree()), new KDTree(new MedianKDTree())};


        for (long size : sizeArray ){

            GeneratePoint points [] = { new RandomGenerate(0, Math.sqrt(size)) ,new LowDiscrepancyGenerate(0, Math.sqrt(size))};

            // Tipo de KDTree > Mean or Median
            for (int i = 0; i < kdtree.length ; i++) {

                // Tipo del GeneratePoint > Random or LowDiscrepancy
                for (int j = 0; j < points.length ; j++) {

                    // For de iteracion

                    for (int k = 0; k < 10 ; k++) {

                        long start = System.nanoTime();
                        kdtree[i].construirKDTree( points[j].generate(size) , KDAxis.horizontal);
                        long time = System.nanoTime() - start;

                        // Datos del experimento
                        ConstructionKDTree construction = new ConstructionKDTree(size);

                        construction.addRepetitions(k);
                        construction.addTime(time);

                        // TODO: incorporar Altura y espacio
//                      construction.addHeight();
                        construction.addSpaceDisk(kdtree[i].getUsedSpace());
//                      construction.addAccessDisk();

                        // statistic
                        statistic[i][j].addConstruction(construction);


                        if ( k >= 3 ){
                            if (statistic[i][j].isLowError())
                                break;
                        }
                    }

                    DataReport dataReport = new DataReport(statistic[i][j], false);

                    if (size == sizeArray[0] )
                        dataReport.makeHeader();

                    dataReport.makeReport();
                    dataReport.flush();
                    dataReport.close();

                    statistic[i][j].clean();

                }
            }
            System.out.println("Evaluando la construccion con tamano: " + size + "\t");
        }



        /**
         *
         * Experimento de las consultas
         *
         * */

        System.out.println("==== Experimento de las consultas ====");

        long sizeArrayQuery = (long)Math.pow(2, 15);

        Statistics statisticQuery[][] = {
                { new QueryStatistics("Consultas", " Media",   "Random" ),
                        new QueryStatistics("Consultas", " Media" ,  "Baja Discrepancia" )},
                { new QueryStatistics("Consultas", " Mediana", "Random"),
                        new QueryStatistics("Consultas", " Mediana", "Baja Discrepancia")}
        };

        // Preparamos el KDTree de consulta
        KDTree tree [][] = {{
                new KDTree(new MeanKDTree()),
                new KDTree(new MeanKDTree())},{

                new KDTree(new MedianKDTree()),
                new KDTree(new MedianKDTree())
                }};


        GeneratePoint points [] = {
                new RandomGenerate(0, Math.sqrt(sizeArrayQuery )),
                new LowDiscrepancyGenerate(0, Math.sqrt(sizeArrayQuery ))
        };


        // Construir el KDTree
        for (int i = 0; i < tree.length ; i++) {
            for (int j = 0; j < tree[0].length ; j++) {
                tree[i][j].construirKDTree( points[i].generate(sizeArrayQuery) ,KDAxis.horizontal);
            }
        }


        // Comenzamos con las consultas


        for (int i = 0; i < tree.length ; i++) {
            for (int j = 0; j < tree[0].length ; j++) {

                for (int k = 0; k < 10 ; k++) {

                    GeneratePoint pointsQuery = new RandomGenerate(0, Math.sqrt(sizeArrayQuery));

                    long time = System.nanoTime();

                    for (KDPoint q : pointsQuery.generate(sizeArrayQuery) )
                        tree[i][j].vecinoMasCercano(q);

                    long timeTotal = System.nanoTime()-time;

                    QueryKDTree query = new QueryKDTree(sizeArrayQuery);

                    query.addSizeArray(sizeArrayQuery);
                    query.addRepetitions(k);
                    //query.addAccessDisk();
                    query.addTimeQuery(timeTotal);
                    statisticQuery[i][j].addQuery(query);

                    if ( k >= 3 ){
                        if (statisticQuery[i][j].isLowError())
                            break;
                    }
                }


                DataReport dataReport = new DataReport(statisticQuery[i][j], false);

                dataReport.makeHeader();
                dataReport.makeReport();
                dataReport.flush();
                dataReport.close();


                statisticQuery[i][j].clean();
            }
        }






    }
}
