package by.gsu.epamlab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LineCorrector {
  private static final String RUBLES = "((\\d{1,3})( +(\\d{3}))*) +((belarusian roubles)|(blr))";
  private static final String DATE = "(\\d{1,2}([/.-])){2}((\\d{4})|(\\d{2}))";
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
          String foundDate = dateMatcher.group();
          String dateDelimiter = dateMatcher.group(2);
          String yearFormat = dateMatcher.group(4) != null ? "yyyy" : "yy";
          try {
              Date date = new SimpleDateFormat("dd" + dateDelimiter + "mm" + dateDelimiter + yearFormat).parse(foundDate);
              Formatter formatter = new Formatter(Locale.ENGLISH);
              formatter.format("%tB %td, %tY", date, date, date);
              finalLine = finalLine.replace(foundDate, formatter.toString());
          } catch (ParseException e) {
              e.printStackTrace();
          }
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