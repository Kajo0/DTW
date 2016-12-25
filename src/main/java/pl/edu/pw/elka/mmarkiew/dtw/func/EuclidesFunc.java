package pl.edu.pw.elka.mmarkiew.dtw.func;

public class EuclidesFunc implements DistanceFunction {

    @Override
    public double distance(final double[] first, final double[] second) {
        if (first.length != second.length)
            throw new IllegalArgumentException();

        double ret = 0;

        for (int i = 0; i < first.length; ++i)
            ret += Math.pow(first[i] - second[i], 2.0);

        return Math.sqrt(ret);
    }

}
