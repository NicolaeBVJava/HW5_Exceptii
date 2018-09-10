package fahrenheit;

import java.io.*;


public class FahrenheitToCelsius {
    public static void main(String[] args) {

    bodyTempCelsiusDegrees("FahrenheitInput.txt","FahrenheitOutput.txt","FahrenheitInvalidData.txt");

    }

    /**
     * @param fahrenheitDegrees: fahrenheit degrees that will be transformed into celsius degrees
     * @return celsius degrees
     */
    public static double fahrenheitToCelsius(double fahrenheitDegrees) {
        return ((fahrenheitDegrees - 32) * 5 / 9);

    }

    /**
     *
     * @param inputFilePath: file path to fahrenheit degrees file
     * @param outputFilePath: file path to file where celsius degrees obtained from fahrenheit degrees will be written
     * @param errFilePath: file path to file where invalid data is written
     *
     * one line is read at a time, so valid lines are the lines that contain only one double number
     *
     */
    public static void bodyTempCelsiusDegrees(String inputFilePath, String outputFilePath, String errFilePath) {
        try (BufferedReader in = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath));
             BufferedWriter err = new BufferedWriter(new FileWriter(errFilePath));
        ) {
            String str = null;
            if ((str = in.readLine()) != null) {
                boolean validInput = false;
                int lineNumber = 0;
                double celsiusDegrees;
                do {
                    lineNumber+=1;
                    try {
                        celsiusDegrees = fahrenheitToCelsius(Double.parseDouble(str));
                        validInput = true;
                        if ((celsiusDegrees >= 36) && (celsiusDegrees <= 43)) {
                            if (celsiusDegrees > 37) {
                                out.write( "Your body temperature is " + celsiusDegrees + ": You are ill!");
                                out.newLine();
                            } else {
                                out.write("Your body temperature is " + celsiusDegrees + ": You are healthy.");
                                out.newLine();
                            }
                        } else {
                            out.write("Your body temperature is " + celsiusDegrees+ ": Check your body temperature again!");
                            out.newLine();
                        }
                    } catch(NumberFormatException nfe){
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