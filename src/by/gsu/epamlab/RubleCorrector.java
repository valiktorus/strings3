package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RubleCorrector extends LineCorrector{
    private static final String RUBLES = "((\\d{1,3})( +(\\d{3}))*) +((belarusian roubles)|(blr))";
    private static final String RUBLE_DELIMITER = " ";
    private static final Pattern RUBLES_PATTERN = Pattern.compile(RUBLES);
    private static final int RUBLES_GROUP_INDEX = 1;

    private static Ruble getCorrectRuble(String rubleLine){
        return new Ruble(createRequiredNumber(rubleLine.split(RUBLE_DELIMITER)));
    }

    private static int createRequiredNumber(String[] rublesWithSpaces){
        StringBuilder builder = new StringBuilder();
        for (String number: rublesWithSpaces) {
            builder.append(number);
        }
        return Integer.parseInt(builder.toString());
    }

    public static String correctLine(String line){
        String finalLine = line;
        Matcher rublesMatcher = RUBLES_PATTERN.matcher(line);
        while (rublesMatcher.find()){
            String rubleLine = rublesMatcher.group(RUBLES_GROUP_INDEX);
            Ruble ruble = getCorrectRuble(rubleLine);
            finalLine = finalLine.replace(rubleLine, ruble.toString());
        }
        return finalLine;
    }
}
