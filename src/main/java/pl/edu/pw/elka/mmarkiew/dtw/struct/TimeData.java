package pl.edu.pw.elka.mmarkiew.dtw.struct;

public class TimeData {

    public long timestamp;
    public double[] values;

    public TimeData(final long timestamp, final double[] values) {
        this.timestamp = timestamp;
        this.values = values;
    }

}