package pl.edu.pw.elka.mmarkiew;

import java.util.Random;

import pl.edu.pw.elka.mmarkiew.dtw.DTW;
import pl.edu.pw.elka.mmarkiew.dtw.struct.TimeSerie;

public class Main {

    public static void main(final String[] args) {
        System.out.println("START");

        TimeSerie s1 = new TimeSerie();
        TimeSerie s2 = new TimeSerie();

        s1.addData(0, new double[] { 1, 1, 3 });
        s1.addData(0, new double[] { 2, 2, 1 });
        s1.addData(0, new double[] { 3, 3, 3 });
        s1.addData(0, new double[] { 4, 4, 2 });
        s2.addData(0, new double[] { 7, 7, 7 });
        s2.addData(0, new double[] { 7, 7, 7 });
        s2.addData(0, new double[] { 7, 7, 7 });
        s1.addData(0, new double[] { 5, 5, 3 });

        s2.addData(0, new double[] { 4, 4, 4 });
        s2.addData(0, new double[] { 5, 5, 4 });
        s2.addData(0, new double[] { 7, 7, 7 });
        s2.addData(0, new double[] { 2, 2, 3 });
        s2.addData(0, new double[] { 3, 3, 4 });
        s2.addData(0, new double[] { 1, 1, 1 });

        // remove(s1, s2);
        //
        // s1.addData(0, new double[] { 1, 2, 3 });
        // s1.addData(0, new double[] { 4, 5, 6 });
        // s1.addData(0, new double[] { 7, 8, 9 });
        //
        // s2.addData(0, new double[] { 1, 2, 3 });
        // s2.addData(0, new double[] { 4, 5, 6 });
        // s2.addData(0, new double[] { 7, 8, 9 });

        System.out.println(new DTW().processEuclides(s1, s2).printPath());
        System.out.println(new DTW().processPlain(s1, s2).getmDistance());
        System.out.println(new DTW().processCumulatePlain(s1, s2).getmDistance());
        System.out.println(new DTW().processSeparatedPlain(s1, s2));
        // // s1.addData(0, new double[] {1,2,3});
        // // s1.addData(0, new double[] {4,5,6});
        // // s1.addData(0, new double[] {7,8,9});
        // //
        // // s2.addData(0, new double[] {1,2,3});
        // // s2.addData(0, new double[] {4,7,8});
        // // s2.addData(0, new double[] {6,3,9});
        // //
        // // System.out.println(new DTW().processEuclides(s1,
        // s2).getmDistance());
        // // System.out.println(new DTW().processPlain(s1, s2).getmDistance());
        // //
        // // System.out.println(new DTW().processEuclides(s1,
        // s2).printMatrixPath());
        // // s1 = new TimeSerie();
        // // s2 = new TimeSerie();
        // // s1.addData(0, new double[] {3.74});
        // // s1.addData(0, new double[] {8.77});
        // // s1.addData(0, new double[] {13.92});
        // //
        // // s2.addData(0, new double[] {3.74});
        // // s2.addData(0, new double[] {11.35});
        // // s2.addData(0, new double[] {11.22});
        // //
        // // System.out.println(new DTW().processEuclides(s1,
        // s2).getmDistance());
        // // System.out.println(new DTW().processPlain(s1, s2).getmDistance());
        //

        // s2.addData(0, new double[] { 1 });
        // s2.addData(0, new double[] { 2 });
        // s2.addData(0, new double[] { 3 });
        // s2.addData(0, new double[] { 4 });
        // s2.addData(0, new double[] { 5 });

        // WarpPath wp = new DTW().processPlain(s1, s2);
        // WarpPath wp = new DTW().processEuclides(s1, s2);
        //
        // System.out.println();
        // // System.out.println(wp.getmMatrix().printMatrix());
        // System.out.println();
        // // System.out.println(wp.printPath());
        // System.out.println();
        // System.out.println(wp.getmDistance());
        // System.out.println();
        // // System.out.println(wp.printMatrixPath());
        // System.out.println();

        System.out.println("END");
    }

    private static void remove(final TimeSerie s1, final TimeSerie s2) {
        Random r = new Random();

        for (int i = 0; i < 200; ++i) {
            s1.addData(0, new double[] { r.nextDouble(), r.nextDouble(), r.nextDouble() });
            // s2.addData(0, new double[] { r.nextDouble(), r.nextDouble(),
            // r.nextDouble() });
        }

    }

}
