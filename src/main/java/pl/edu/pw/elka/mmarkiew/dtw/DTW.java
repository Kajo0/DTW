package pl.edu.pw.elka.mmarkiew.dtw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.elka.mmarkiew.dtw.func.CumulatePlainFunc;
import pl.edu.pw.elka.mmarkiew.dtw.func.DistanceFunction;
import pl.edu.pw.elka.mmarkiew.dtw.func.EuclidesFunc;
import pl.edu.pw.elka.mmarkiew.dtw.func.PlainFunc;
import pl.edu.pw.elka.mmarkiew.dtw.struct.Matrix;
import pl.edu.pw.elka.mmarkiew.dtw.struct.Pair;
import pl.edu.pw.elka.mmarkiew.dtw.struct.TimeSerie;
import pl.edu.pw.elka.mmarkiew.dtw.struct.WarpPath;

public class DTW {

    public WarpPath processEuclides(final TimeSerie patternSerie, final TimeSerie capturedSerie) {
        return process(patternSerie, capturedSerie, new EuclidesFunc());
    }

    public WarpPath processPlain(final TimeSerie patternSerie, final TimeSerie capturedSerie) {
        return process(patternSerie, capturedSerie, new PlainFunc());
    }

    public WarpPath processCumulatePlain(final TimeSerie patternSerie, final TimeSerie capturedSerie) {
        return process(patternSerie, capturedSerie, new CumulatePlainFunc());
    }

    public double processSeparatedPlain(final TimeSerie patternSerie, final TimeSerie capturedSerie) {
        double result = 0;

        List<TimeSerie> separatedPattern = new ArrayList<TimeSerie>();
        List<TimeSerie> separatedCaptured = new ArrayList<TimeSerie>();

        for (int i = 0; i < patternSerie.getSingleDataLength(); ++i) {
            separatedPattern.add(new TimeSerie());
            separatedCaptured.add(new TimeSerie());
        }

        for (int i = 0; i < patternSerie.getSize(); ++i) {
            long patternTimestamp = patternSerie.getTimestamp(i);
            double[] patternData = patternSerie.getData(i);

            for (int j = 0; j < patternData.length; ++j)
                separatedPattern.get(j).addData(patternTimestamp, new double[] { patternData[j] });
        }

        for (int i = 0; i < capturedSerie.getSize(); ++i) {
            long capturedTimestamp = capturedSerie.getTimestamp(i);
            double[] capturedData = capturedSerie.getData(i);

            for (int j = 0; j < capturedData.length; ++j)
                separatedCaptured.get(j).addData(capturedTimestamp,
                        new double[] { capturedData[j] });
        }

        for (int i = 0; i < patternSerie.getSingleDataLength(); ++i)
            result += processPlain(separatedPattern.get(i), separatedCaptured.get(i))
                    .getmDistance();

        return result;
    }

    private WarpPath process(final TimeSerie patternSerie, final TimeSerie capturedSerie,
            final DistanceFunction fDistance) {
        if (patternSerie.getSize() == 0 || capturedSerie.getSize() == 0)
            throw new RuntimeException();

        Matrix matrix = fillMatrix(patternSerie, capturedSerie, fDistance);

        return calculatePath(matrix);
    }

    private Matrix fillMatrix(final TimeSerie patternSerie, final TimeSerie capturedSerie,
            final DistanceFunction fDistance) {
        Matrix matrix = new Matrix(capturedSerie.getSize(), patternSerie.getSize());

        matrix.setValue(0, 0, fDistance.distance(patternSerie.getData(0), capturedSerie.getData(0)));

        for (int i = 1; i < patternSerie.getSize(); ++i) {
            matrix.setValue(
                    i,
                    0,
                    fDistance.distance(patternSerie.getData(i), capturedSerie.getData(0))
                            + matrix.getValue(i - 1, 0));

            for (int j = 1; j < capturedSerie.getSize(); ++j) {
                matrix.setValue(0, j,
                        fDistance.distance(patternSerie.getData(0), capturedSerie.getData(j))
                                + matrix.getValue(0, j - 1));

                double val = fDistance.distance(patternSerie.getData(i), capturedSerie.getData(j))
                        + Math.min(matrix.getValue(i - 1, j - 1),
                                Math.min(matrix.getValue(i - 1, j), matrix.getValue(i, j - 1)));

                matrix.setValue(i, j, val);
            }
        }

        return matrix;
    }

    private WarpPath calculatePath(final Matrix matrix) {
        WarpPath wp = new WarpPath(matrix);
        List<Pair<Integer>> l = new ArrayList<Pair<Integer>>();

        for (int j = matrix.getRowsNum() - 1; j >= 0;) {
            for (int i = matrix.getColsNum() - 1; i >= 0;) {
                l.add(new Pair<Integer>(i, j));

                double max = matrix.getValue(i, j);
                int nextI = -1;
                int nextJ = -1;

                if (i > 0)
                    if (matrix.getValue(i - 1, j) <= max) {
                        max = matrix.getValue(i - 1, j);
                        nextI = i - 1;
                        nextJ = j;
                    }

                if (i > 0 && j > 0)
                    if (matrix.getValue(i - 1, j - 1) <= max) {
                        max = matrix.getValue(i - 1, j - 1);
                        nextI = i - 1;
                        nextJ = j - 1;
                    }

                if (j > 0)
                    if (matrix.getValue(i, j - 1) <= max) {
                        max = matrix.getValue(i, j - 1);
                        nextI = i;
                        nextJ = j - 1;
                    }

                i = nextI;
                j = nextJ;
            }
        }

        Collections.reverse(l);

        for (Pair<Integer> p : l)
            wp.addPathPoint(p.first, p.second);

        return wp;
    }

}
