package by.gsu.epamlab;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateCorrector extends LineCorrector{
    private static final String DATE = "(\\d{1,2})[/.-](\\d{1,2})[/.-]((\\d{4})|(\\d{2}))";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE);
    private static final String FINAL_DATE_FORMAT = "%tB %td, %tY";
    private static final String MILLENNIUM_YEAR_PREFIX = "20";
    private static final int DAY_GROUP = 1;
    private static final int MONTH_GROUP = 2;
    private static final int FULL_YEAR_GROUP = 4;
    private static final int MONTH_CORRECTOR = 1;
    private static final int SMALL_YEAR_GROUP = 5;

    @Override
    protected String getReplacedPart(Matcher matcher) {
        return matcher.group();
    }

    @Override
    protected Pattern getPattern() {
        return DATE_PATTERN;
    }

    @Override
    protected String getFixedString(Matcher matcher) {
        Calendar calendar = convertInputLineToCalendar(matcher);
        Formatter formatter = new Formatter(Locale.ENGLISH);
        return formatter.format(FINAL_DATE_FORMAT, calendar, calendar, calendar).toString();
    }

    private static Calendar convertInputLineToCalendar(Matcher dateMatcher) {
        int day = Integer.parseInt(dateMatcher.group(DAY_GROUP));
        int month = Integer.parseInt(dateMatcher.group(MONTH_GROUP));
        String yearString = dateMatcher.group(FULL_YEAR_GROUP);
        if (yearString == null) {
            yearString = MILLENNIUM_YEAR_PREFIX + dateMatcher.group(SMALL_YEAR_GROUP);
        }
        int year = Integer.parseInt(yearString);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - MONTH_CORRECTOR, day);
        return calendar;
    }
}
