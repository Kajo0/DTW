package pl.edu.pw.elka.mmarkiew.dtw.struct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.edu.pw.elka.mmarkiew.dtw.util.FileUtils;

public class TimeSerie implements Iterable<TimeData> {

    private List<TimeData> mDataSerie;

    public TimeSerie() {
        mDataSerie = new ArrayList<TimeData>();
    }

    public TimeSerie(final List<TimeData> data) {
        mDataSerie = new ArrayList<TimeData>(data);
    }

    public TimeSerie(final String dataString) {
        mDataSerie = FileUtils.convertStringToPoints(dataString);
    }

    public TimeSerie(final String dir, final String filename) throws IOException {
        mDataSerie = FileUtils.convertStringToPoints(FileUtils.loadFromFile(dir, filename));
    }

    public void addData(final long timestamp, final double[] data) {
        if (!mDataSerie.isEmpty() && getSingleDataLength() != data.length)
            throw new IllegalArgumentException();

        mDataSerie.add(new TimeData(timestamp, data));
    }

    public int getSize() {
        return mDataSerie.size();
    }

    public int getSingleDataLength() {
        if (mDataSerie.isEmpty())
            throw new IndexOutOfBoundsException();

        return mDataSerie.get(0).values.length;
    }

    public long getTimestamp(final int i) {
        return mDataSerie.get(i).timestamp;
    }

    public double[] getData(final int i) {
        return mDataSerie.get(i).values;
    }

    @Override
    public Iterator<TimeData> iterator() {
        return mDataSerie.iterator();
    }

}
