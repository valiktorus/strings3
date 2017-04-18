import by.gsu.epamlab.LineCorrector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("src/in.txt"));
        String inputLine;
        String outputLine;
        while (scanner.hasNextLine()){
            inputLine = scanner.nextLine();
            System.out.println(LineCorrector.correctLine(inputLine));
        }
    }
}
