package cl.dcc;

import java.util.ArrayList;
import java.util.List;

public class MedianKDTree extends SplitMethod {

    public double getSplitPoint(List<KDPoint> list, boolean splitaxis) {

        if (list.size() == 1)
            return list.get(0).getCoord(splitaxis);

        int mediansCount = (int) Math.ceil(list.size() / 5.0);

        List<KDPoint> medians = new ArrayList<KDPoint>();

        // Iteramos sobre los grupos
        for (int i = 0; i < mediansCount; i++) {

            int groupLeft = i * 5;
            int groupRight = Math.min(groupLeft + 5, list.size());

            // Ordenamos y obtenemos la mediana de cada grupo
            List<KDPoint> sortedGroup = insertionSort(list.subList(groupLeft, groupRight), splitaxis);
            medians.add(sortedGroup.get(Math.round((groupRight - groupLeft) / 2)));
        }
        // select the median from the contiguous block
        return getSplitPoint(medians, splitaxis);
    }

    private List<KDPoint> insertionSort(List<KDPoint> data, boolean splitaxis) {
        for (int i = 1; i < data.size(); i++) {
            KDPoint aux = data.get(i);
            int j;

            for (j = i - 1; j >= 0 && data.get(j).getCoord(splitaxis) > aux.getCoord(splitaxis); j--)
                data.set(j + 1, data.get(j));

            data.set(j + 1, aux);
        }

        return data;
    }
}
