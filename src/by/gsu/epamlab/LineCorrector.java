package by.gsu.epamlab;

public abstract class LineCorrector {

  public static String correctLine(String line){

      String finalLine = RubleCorrector.correctLine(line);
      finalLine = DateCorrector.correctLine(finalLine);

      return finalLine;
  }
}