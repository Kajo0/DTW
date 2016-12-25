package pl.edu.pw.elka.mmarkiew.dtw.struct;

/**
 * <code>
 * (0,2)|(1,2)|(2,2)|(3,2)<br />
 * -----+-----+-----+-----<br />
 * (0,1)|(1,1)|(2,1)|(3,1)<br />
 * -----+-----+-----+-----<br />
 * (0,0)|(1,0)|(2,0)|(3,0)<br />
 * </code>
 * 
 */
public class Matrix {

    private double[][] mCells;

    public Matrix(final int rows, final int cols) {
        mCells = new double[rows][cols];

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                mCells[i][j] = -1;
    }

    public double getValue(final int x, final int y) {
        return mCells[mCells.length - 1 - y][x];
    }

    public void setValue(final int x, final int y, final double value) {
        mCells[mCells.length - 1 - y][x] = value;
    }

    public int getRowsNum() {
        return mCells.length;
    }

    public int getColsNum() {
        return mCells[0].length;
    }

    public String printMatrix() {
        StringBuilder sb = new StringBuilder("\t");

        for (int i = 0; i < mCells.length; ++i) {
            if (i > 0)
                sb.append("\n\t");

            for (int j = 0; j < mCells[0].length; ++j) {
                if (j > 0)
                    sb.append("\t ");

                sb.append(mCells[i][j]);
            }
        }

        return sb.toString();
    }

}
