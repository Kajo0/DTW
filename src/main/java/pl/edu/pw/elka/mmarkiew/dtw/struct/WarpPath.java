package pl.edu.pw.elka.mmarkiew.dtw.struct;

import java.util.ArrayList;
import java.util.List;

public class WarpPath {

    private double mDistance;
    private List<Pair<Integer>> mPath;
    private Matrix mMatrix;

    public WarpPath(final Matrix matrix) {
        mPath = new ArrayList<Pair<Integer>>();
        mMatrix = matrix;

        mDistance = matrix.getValue(matrix.getColsNum() - 1, matrix.getRowsNum() - 1);
    }

    public void addPathPoint(final int x, final int y) {
        Pair<Integer> pair = new Pair<Integer>(x, y);

        if (mPath.contains(pair))
            throw new IllegalArgumentException("Already contains such point");

        // mDistance += mMatrix.getValue(x, y);
        mPath.add(pair);
    }

    public double getmDistance() {
        return mDistance;
    }

    public void setmDistance(final double distance) {
        this.mDistance = distance;
    }

    public Matrix getmMatrix() {
        return mMatrix;
    }

    public String printPath() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (Pair<Integer> p : mPath)
            sb.append("(" + p.first + "," + p.second + ")");

        sb.append("]");

        return sb.toString();
    }

    public String printMatrixPath() {
        StringBuilder sb = new StringBuilder("\t");

        for (int i = mMatrix.getRowsNum() - 1; i >= 0; --i) {
            if (i != mMatrix.getRowsNum() - 1)
                sb.append("\n\t");

            for (int j = 0; j < mMatrix.getColsNum(); ++j) {
                if (j > 0)
                    sb.append("\t ");

                if (mPath.contains(new Pair<Integer>(j, i)))
                    sb.append(String.format("{%07.3f}", mMatrix.getValue(j, i)));
                else
                    sb.append(String.format("[%07.3f]", mMatrix.getValue(j, i)));
            }
        }

        return sb.toString();
    }

}
