package cl.dcc;

public class Main {

    public static void main(String[] args) {

        // Experimento en memoria principal
        int minSizeArray    = 10;
        int maxSizeArray    = 20;

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


        for (long size : sizeArray ){

            KDTree kdtree []        = {new KDTree(new MeanKDTree()), new KDTree(new MedianKDTree())};
            GeneratePoint points [] = { new RandomGenerate(0, Math.sqrt(size)) ,new LowDiscrepancyGenerate(0, Math.sqrt(size))};

            // Tipo de KDTree > Mean or Median
            for (int i = 0; i < kdtree.length ; i++) {

                // Tipo del GeneratePoint > Random or LowDiscrepancy
                for (int j = 0; j < points.length ; j++) {

                    // For de iteracion

                    for (int k = 0; k < 1000 ; k++) {

                        long start = System.nanoTime();
                        kdtree[i].construirKDTree( points[j].generate(size) , KDAxis.horizontal);
                        long time = System.nanoTime() - start;

                        // Datos del experimento
                        ConstructionKDTree construction = new ConstructionKDTree(size);

                        construction.addRepetitions(k);

                        construction.addTime(time);

                        // TODO: incorporar Altura y espacio
//                      construction.addHeight();
//                      construction.addSpaceDisk();
//                      construction.addAccessDisk();

                        // statistic
                        statistic[i][j].addConstruction(construction);


                        if ( k >= 3 ){
                            if (statistic[i][j].isLowError())
                                break;
                        }
                    }

                    DataReport dataReport = new DataReport(statistic[i][j], false);
                    dataReport.makeReport();
                }
            }




        }


        /*
        List<KDPoint> P = new ArrayList<KDPoint>();
        for (int i = 0; i < 100; i++) {
            P.add(new KDPoint(Math.random() * Math.sqrt(P.size()), Math.random() * Math.sqrt(P.size())));

        KDPoint q = new KDPoint(Math.random() * Math.sqrt(P.size()),
                Math.random() * Math.sqrt(P.size()));

        KDPoint result = tree.vecinoMasCercano(q);

        System.out.println("Vecino más cercano a Q [" + q + "]: " + result);
        for (int i = 0; i < 10; i++) {
            System.out.println("Distancia a punto " + i + " [" + P.get(i) + "]: " + P.get(i).distTo(q));
        }
        */
    }
}
