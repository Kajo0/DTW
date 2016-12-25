package pl.edu.pw.elka.mmarkiew.dtw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.mmarkiew.dtw.struct.TimeData;

public class FileUtils {

    public static void saveToFile(final String dir, final String filename, final String data)
            throws IOException {
        FileOutputStream out;

        File file = new File(dir, filename);

        if (!file.exists())
            file.getParentFile().mkdirs();

        out = new FileOutputStream(file);
        out.write(data.getBytes());
        out.close();
    }

    public static String loadFromFile(final String dir, final String filename) throws IOException {
        StringBuffer buffer = new StringBuffer();

        File file = new File(dir, filename);
        FileInputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String str;
        if (in != null) {
            while ((str = reader.readLine()) != null)
                buffer.append(str + "\n");

            in.close();
        }

        return buffer.toString();
    }

    public static String convertPointsToString(final List<TimeData> data) {
        StringBuilder sb = new StringBuilder();

        for (TimeData d : data) {
            sb.append(d.timestamp);
            for (double db : d.values)
                sb.append("|" + db);
            sb.append("\n");
        }

        return sb.toString();
    }

    public static List<TimeData> convertStringToPoints(final String dataString) {
        List<TimeData> data = new ArrayList<TimeData>();

        for (String line : dataString.split("\n")) {
            String[] str = line.split("\\|");
            long t = 0;
            double[] v = new double[str.length - 1];

            for (int i = 0; i < str.length; ++i)
                if (i == 0)
                    t = Long.parseLong(str[i]);
                else
                    v[i - 1] = Double.parseDouble(str[i]);

            data.add(new TimeData(t, v));
        }

        return data;
    }

}
