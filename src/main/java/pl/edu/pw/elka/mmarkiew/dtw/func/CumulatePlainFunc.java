package pl.edu.pw.elka.mmarkiew.dtw.func;

public class CumulatePlainFunc implements DistanceFunction {

    @Override
    public double distance(final double[] first, final double[] second) {
        if (first.length != second.length)
            throw new IllegalArgumentException();

        double ret1 = 0;
        double ret2 = 0;

        for (int i = 0; i < first.length; ++i) {
            ret1 += first[i] * first[i];
            ret2 += second[i] * second[i];
        }

        return Math.abs(Math.sqrt(ret1) - Math.sqrt(ret2));
    }

}
