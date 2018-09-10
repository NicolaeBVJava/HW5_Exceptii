package max;

import java.io.*;

public class CalculateMax {
    public static void main(String[] args) {
        calculateMax("MaxInput.txt", "MaxOutput.txt", "MaxInvalidData.txt");
    }

    /**
     * metoda calculeaza maximul dintre doua numere de tip double
     *
     * @param a primul numar
     * @param b al doilea numar
     * @return maximul dintre numerele a si b
     */
    static double getMax(double a, double b) {

        return (a > b ? a : b);
    }

    /**
     *
     * @param inputFilePath:  file path to a file with numbers
     * @param outputFilePath: file path to file where the biggest of them will be written
     * @param errFilePath:    file path to file where "NumberFormatException","Empty file!", "There are no double numbers" kind of errors will be written
     *
     * one line is read at a time, so valid lines are the lines that contain only one double number
     *
     * returns the biggest number from o collection of numbers
     */
    public static void calculateMax(String inputFilePath, String outputFilePath, String errFilePath) {
        try (BufferedReader in = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath));
             BufferedWriter err = new BufferedWriter(new FileWriter(errFilePath));
        ) {
            String str = null;
            if ((str = in.readLine()) != null) {
                double max = Double.MIN_VALUE;
                boolean validInput = false;
                int lineNumber = 0 ;
                do {
                    lineNumber+=1;
                    try {
                        max = getMax(max, Double.parseDouble(str));
                        validInput = true;
                    } catch (NumberFormatException nfe) {
                        err.write("Invalid input at line number " + (lineNumber) + ": " + str);
                        err.newLine();
                    }
                }
                while ((str = in.readLine()) != null);
                if (validInput) {
                    out.write("The max value is: " + max);
                    out.newLine();
                } else {
                    err.write("There is no valid input!");
                    err.newLine();
                }
            } else {
                err.write("Empty file!");
            }
        } catch (IOException e) {
                System.err.println("Something is wrong with the files!");
        }
    }
}