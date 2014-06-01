package cl.dcc;

/**
 * Created by Gonzaloafa on 31-05-2014.
 */
import java.util.ArrayList;

class Statistics {

    private int arraySize;

    ArrayList<AlgorithmStatistic> evaluations;

    public double error, meanTime, devStd, meanComparisons;
    public int repetitions;
    private AlgorithmStatistic currentEvaluation;

    public Statistics(int arraySize) {
        evaluations = new ArrayList<AlgorithmStatistic>(1000);
        this.arraySize = arraySize;
    }

    public void updateMeasurement(Algorithms algorithm, int replay) {
        long comparisons = algorithm.getComparisons();
        double executionTime = algorithm.getExecutionTime();

        currentEvaluation = new AlgorithmStatistic(comparisons, executionTime);
        repetitions = replay;

        evaluations.add(currentEvaluation);

        meanTime = computeMeanTime();
        meanComparisons = computeMeanComparisons();

        if (replay > 1) {
            devStd = standardDeviation();
            error = devStd * 100 / meanTime;
        }
    }

    public boolean doContinue() {
        return error < (5 * meanTime);
    }

    public double computeMeanTime() {
        double sum = 0;

        for (AlgorithmStatistic measurement : evaluations)
            sum += measurement.executionTime;

        return sum / evaluations.size();
    }

    public double computeMeanComparisons() {
        long sum = 0;

        for (AlgorithmStatistic measurement : evaluations)
            sum += measurement.comparisons;

        return sum / evaluations.size();
    }

    public double standardDeviation() {

        double sum = 0;

        for (AlgorithmStatistic measurement : evaluations)
            sum += Math.pow(measurement.executionTime - meanTime, 2);

        return Math.sqrt(sum / (evaluations.size() - 1));
    }

    @Override
    public String toString() {
        return repetitions + ";" + error + ";" + arraySize + ";" + currentEvaluation
                + ";" + meanComparisons + ";" + meanTime;
    }

}