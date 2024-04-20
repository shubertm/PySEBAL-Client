package infbyte.pysebal;

import jline.internal.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class PySEBAL {
    public static void run(String path, String lastRow) {
        try {
            String projectRoot = (new File("./")).getCanonicalPath();
            String sebalPath = projectRoot + "/PySEBAL_dev/SEBAL";
            String py3Path = sebalPath + "/Run_py3.py";
            ProcessBuilder processBuilder = new ProcessBuilder("python", py3Path, path, lastRow);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = process.inputReader();
            try {
                while (reader.readLine() != null) {
                    System.out.println(reader.readLine());;
                }
            } catch(NullPointerException e) {
                Log.debug(e);
            }
            /* try (PythonInterpreter interpreter = new PythonInterpreter()) {
                interpreter.exec(sebalPath);
                interpreter.exec(
                        """
                                import sys
                                sys.path.append("{}".format(sebal_path))"""
                );
                interpreter.exec(
                        """
                                import os
                                os.chdir("{}".format(sebal_path))"""
                );
                System.out.println(interpreter.getSystemState().getCurrentWorkingDir());
                interpreter.exec("import Run_py3");
                // PyObject runPy3 = interpreter.get("runPy3");
                // runPy3.__call__(new PyString(path));
            }*/
        } catch (IOException e) {
            Log.trace(e);
        }
    }
}