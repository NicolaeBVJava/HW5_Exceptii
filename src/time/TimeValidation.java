package time;

import java.io.*;

public class TimeValidation {
    public static void main(String[] args) {
        writeTime("TimeInput.txt","TimeOutput.txt","TimeInvalidData.txt");
    }

    /**
     *
     * @param h
     * @param m
     * @return
     *
     */
    public static boolean checkTime(String h, String m) {
        try {
            int hh = Integer.parseInt(h);
            int mm = Integer.parseInt(m);
            return ((hh >= 0) && (hh <= 23)) && ((mm >= 0) && (mm <= 59));
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     *
     * @param inputFilePath: file path to input data
     * @param outputFilePath: file path to file where time will be written
     * @param errFilePath: file path to file where invalid data is written
     *
     * one line is read at a time, so valid lines are the lines that contains two integers separated by space
     */
    public static void writeTime(String inputFilePath, String outputFilePath, String errFilePath) {
        try (BufferedReader in = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath));
             BufferedWriter err = new BufferedWriter(new FileWriter(errFilePath));
        ) {
            String str = null;
            if ((str = in.readLine()) != null) {
                boolean validInput = false;
                String[] hhmm = null;
                int lineNumber = 0;
                do {
                    lineNumber+=1;
                        if (((hhmm = str.split("\\s")).length == 2) && checkTime(hhmm[0],hhmm[1])){
                                validInput = true;
                                out.write( "The hour is: " + hhmm[0] + " the minute is: "+hhmm[1]);
                                out.newLine();
                        }
                        else{
                            err.write("Invalid input at line number " + lineNumber + ": " + str);
                            err.newLine();
                        }
                } while ((str = in.readLine()) != null);
                if (!validInput){
                    err.write("There is no valid input!");
                    err.newLine();
                }
            }
            else{
                err.write("Empty file!");
            }
        }
        catch(IOException ioe){
            System.err.println("Something is wrong with the files!");
        }
    }
}