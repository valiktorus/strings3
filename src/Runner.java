import by.gsu.epamlab.LineCorrector;

import java.io.*;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = null;
        BufferedWriter bufferedWriter = null;
        try {
            scanner = new Scanner(new FileReader(Constants.INPUT_FILE_NAME));
            bufferedWriter = new BufferedWriter(new FileWriter(Constants.OUTPUT_FILE_NAME));
            String inputLine;
            String outputLine;
            String lineDelimiter = Constants.EMPTY_LINE;
            while (scanner.hasNextLine()) {
                inputLine = scanner.nextLine();
                outputLine = lineDelimiter + LineCorrector.correctLine(inputLine);
                if (lineDelimiter.isEmpty()){
                    lineDelimiter = Constants.LINE_DELIMITER;
                }
                bufferedWriter.write(outputLine);
            }
        }catch (FileNotFoundException e) {
            System.err.println(Constants.INPUT_FILE_IS_NOT_FOUND);
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
