package by.gsu.epamlab;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LineCorrector {
  private static final String RUBLES = "((\\d{1,3})( +(\\d{3}))*) +((belarusian roubles)|(blr))";
  private static final String DATE = "(\\d{1,2}([\\/\\.-])){2}\\d{2,4}";
  private static final Pattern RUBLES_PATTERN = Pattern.compile(RUBLES);
  private static final Pattern DATE_PATTERN = Pattern.compile(DATE);

  public static String correctLine(String line){
      String finalLine = line;
      Matcher rublesMatcher = RUBLES_PATTERN.matcher(line);
      while (rublesMatcher.find()){
          String rublesWithSpaces = rublesMatcher.group(1);
          finalLine = finalLine.replace(rublesWithSpaces, createRequiredNumber(rublesWithSpaces.split(" ")) );
      }
      Matcher dateMatcher = DATE_PATTERN.matcher(finalLine);
      while (dateMatcher.find()){
          String[] dateWithoutSeparator = dateMatcher.group().split(dateMatcher.group(2));
          Calendar dateFromLine = new GregorianCalendar(
                  Integer.parseInt(dateWithoutSeparator[2]),
                  Integer.parseInt(dateWithoutSeparator[1]),
                  Integer.parseInt(dateWithoutSeparator[0])
          );
          System.out.println(dateFromLine);

      }

      return finalLine;
  }

  private static String createRequiredNumber(String[] rublesWithSpaces){
      StringBuilder builder = new StringBuilder();
      for (String number: rublesWithSpaces) {
          builder.append(number);
      }
      return builder.toString();
  }
}