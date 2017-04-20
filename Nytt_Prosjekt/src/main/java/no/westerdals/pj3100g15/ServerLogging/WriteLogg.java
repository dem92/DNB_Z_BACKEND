package no.westerdals.pj3100g15.ServerLogging;

import java.io.*;
import java.util.Calendar;

public class WriteLogg {
    /**
     * Creates a textfile, if it does not exists, and writes data about the exception to it.
     * It writes when, what exception it is, the stacktrace and the description of the exception to the file.
     * The purpose is debugging when the server is shutting down when deployed on an external server.
     * @param exception Exception-object
     */
    public static void writeLogg(Exception exception) {
        String filename = "errormessages.txt";
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        if (!new File(filename).exists()) {
            new File(filename);
        }
        try {
            fileWriter = new FileWriter(filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("---------  " + exception.toString() + "  -----------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(currentTimestamp.toString());
            bufferedWriter.newLine();
            if (exception.getMessage() == null) {
                bufferedWriter.write("null");
            } else {
                bufferedWriter.write(exception.getMessage());
            }
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            bufferedWriter.write(exceptionAsString);
            bufferedWriter.newLine();
            bufferedWriter.write(exception.toString());
            bufferedWriter.write(exception.getStackTrace().toString());

            bufferedWriter.newLine();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
