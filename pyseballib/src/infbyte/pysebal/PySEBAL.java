package infbyte.pysebal;

import infbyte.pysebal.listeners.ImageAnalysisListener;
import jline.internal.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class PySEBAL {
    public static void run(String path, String lastRow, ImageAnalysisListener listener) {
        int imageNo;
        try {
            String projectRoot = (new File("./")).getCanonicalPath();
            String sebalPath = projectRoot + "/PySEBAL_dev/SEBAL";
            String py3Path = sebalPath + "/Run_py3.py";
            ProcessBuilder processBuilder = new ProcessBuilder("python", py3Path, path, lastRow);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = process.inputReader();
            try {
                String str = "starting line num: ";
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains(str)) {
                        int index = Integer.parseInt(line.substring(19));
                        imageNo = index - 1;
                        listener.onNextImage(imageNo);
                    }
                    System.out.println(line);
                    line = reader.readLine();
                }
            } catch(NullPointerException e) {
                Log.debug(e);
            }
        } catch (IOException e) {
            Log.trace(e);
        }
    }
}