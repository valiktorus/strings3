package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RubleCorrector extends LineCorrector{
    private static final String RUBLES = "(((\\d{1,3})(\\s+(\\d{3}))*)\\s*)\\s((belarusian roubles)|(blr))";
    private static final String RUBLE_DELIMITER = "\\s";
    private static final Pattern RUBLES_PATTERN = Pattern.compile(RUBLES);
    private static final int RUBLE_VALUE_GROUP_INDEX = 1;
    private static final String EMPTY_STRING = "";

    @Override
    protected String getReplacedPart(Matcher matcher) {
        return matcher.group(RUBLE_VALUE_GROUP_INDEX);
    }

    @Override
    protected Pattern getPattern() {
        return RUBLES_PATTERN;
    }

    @Override
    protected String getFixedString(Matcher matcher) {
        String rubleLine = matcher.group(RUBLE_VALUE_GROUP_INDEX);
        return rubleLine.replaceAll(RUBLE_DELIMITER, EMPTY_STRING);
    }
}
