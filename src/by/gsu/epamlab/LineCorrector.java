package by.gsu.epamlab;

import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;
import org.omg.PortableInterceptor.INACTIVE;

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
  private static final String FULL_YEAR_FORMAT = "yyyy";
  private static final String SMALL_YEAR_FORMAT = "yy";
  private static final String DAY_FORMAT = "dd";
  private static final String MONTH_FORMAT = "MM";
  private static final String FINAL_DATE_FORMAT = "%tB %td, %tY";
  private static final String RUBLE_DELIMITER = " ";
  private static final int RUBLES_GROUP_INDEX = 1;
  private static final int DELIMITER_GROUP_INDEX = 2;
  private static final int YEAR_GROUP_INDEX = 4;

  public static String correctLine(String line){
      String finalLine = line;
      Matcher rublesMatcher = RUBLES_PATTERN.matcher(line);
      while (rublesMatcher.find()){
          String rublesWithSpaces = rublesMatcher.group(RUBLES_GROUP_INDEX);
          finalLine = finalLine.replace(rublesWithSpaces, createRequiredNumber(rublesWithSpaces.split(RUBLE_DELIMITER)) );
      }
      Matcher dateMatcher = DATE_PATTERN.matcher(finalLine);
      while (dateMatcher.find()){
          String foundDate = dateMatcher.group();
          String dateDelimiter = dateMatcher.group(DELIMITER_GROUP_INDEX);
          String yearFormat = dateMatcher.group(YEAR_GROUP_INDEX) != null ? FULL_YEAR_FORMAT : SMALL_YEAR_FORMAT;
          try {
              Date date = new SimpleDateFormat(DAY_FORMAT + dateDelimiter + MONTH_FORMAT + dateDelimiter + yearFormat).parse(foundDate);
              Formatter formatter = new Formatter(Locale.ENGLISH);
              formatter.format(FINAL_DATE_FORMAT, date, date, date);
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