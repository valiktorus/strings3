import by.gsu.epamlab.LineCorrector;

import java.io.*;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = null;
        BufferedWriter bufferedWriter = null;
        try {
            scanner = new Scanner(new FileReader("src/in.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("src/out.txt"));
            String inputLine;
            String outputLine;
            String lineDelimiter = "";
            while (scanner.hasNextLine()) {
                inputLine = scanner.nextLine();
                outputLine = lineDelimiter + LineCorrector.correctLine(inputLine);
                lineDelimiter = "\n";
                bufferedWriter.write(outputLine);
            }
        }catch (FileNotFoundException e) {
            System.err.println("Input file not found");
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (scanner != null){
                scanner.close();
            }
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
