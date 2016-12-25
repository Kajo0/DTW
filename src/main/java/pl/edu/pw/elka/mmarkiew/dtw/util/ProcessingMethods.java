package pl.edu.pw.elka.mmarkiew.dtw.util;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.mmarkiew.dtw.struct.TimeData;

public class ProcessingMethods {

    public static final double DEFAULT_FILTER_TRESHOLD_EPSYLON = 1.0d;
    public static final double DEFAULT_FILTER_DIFFERENCE_DELTA = 0.5d;

    public enum Methods {
        EUCLIDES, PLAIN, CUMULATED_PLAIN, SEPARATED;
    }

    public static List<TimeData> filterDefault(final List<TimeData> data) {
        return filterDifferenceDelta(filterTresholdEpsylon(data, DEFAULT_FILTER_TRESHOLD_EPSYLON),
                DEFAULT_FILTER_DIFFERENCE_DELTA);
    }

    public static List<TimeData> filterTresholdEpsylon(final List<TimeData> data,
            final double treshold) {
        ArrayList<TimeData> filteredData = new ArrayList<TimeData>();

        // First
        for (TimeData d : data) {
            double sum = 0;

            for (double v : d.values)
                sum += Math.abs(v);

            if (sum > treshold)
                filteredData.add(d);
        }

        return filteredData;
    }

    public static List<TimeData> filterDifferenceDelta(final List<TimeData> data,
            final double epsilon) {
        ArrayList<TimeData> filteredData = new ArrayList<TimeData>();

        TimeData lastD = null;
        for (int i = 0; i < data.size() - 1; ++i) {
            if (i == 0) {
                lastD = data.get(i);
                filteredData.add(lastD);
                continue;
            }

            int overload = 0;
            TimeData d2 = data.get(i);

            for (int j = 0; j < lastD.values.length; ++j)
                if (Math.abs(d2.values[j] - lastD.values[j]) < epsilon)
                    ++overload;
                else
                    break;

            if (overload != lastD.values.length) {
                filteredData.add(d2);
                lastD = d2;
            }
        }

        return filteredData;
    }

}
