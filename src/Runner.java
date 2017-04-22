import by.gsu.epamlab.util.LineCorrectorUtil;

import java.io.*;

public class Runner {
    public static void main(String[] args) {

        try(BufferedReader in = new BufferedReader(new FileReader(Constants.INPUT_FILE_NAME));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Constants.OUTPUT_FILE_NAME)))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(Constants.EMPTY_LINE + LineCorrectorUtil.correct(inputLine));
            }
        } catch (IOException e) {
            System.err.println("Error occurred while processing file. " + e.getMessage());
        }
    }
}
