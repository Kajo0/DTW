package pl.edu.pw.elka.mmarkiew.dtw.func;

public class PlainFunc implements DistanceFunction {

    @Override
    public double distance(final double[] first, final double[] second) {
        if (first.length != second.length)
            throw new IllegalArgumentException();

        double ret = 0;

        for (int i = 0; i < first.length; ++i)
            ret += Math.abs(first[i] - second[i]);

        return ret;
    }

}
