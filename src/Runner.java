import by.gsu.epamlab.LineCorrector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("src/in.txt"));
            String inputLine;
            String outputLine;
            while (scanner.hasNextLine()) {
                inputLine = scanner.nextLine();
                System.out.println(LineCorrector.correctLine(inputLine));
            }
        }catch (FileNotFoundException e){
            System.err.println("Input file not found");
        }finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }
}
