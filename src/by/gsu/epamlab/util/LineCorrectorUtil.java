package by.gsu.epamlab.util;

import by.gsu.epamlab.DateCorrector;
import by.gsu.epamlab.LineCorrector;
import by.gsu.epamlab.RubleCorrector;

import java.util.HashSet;
import java.util.Set;

public class LineCorrectorUtil {
    private static final Set<LineCorrector> CORRECTORS = new HashSet<>();
    static {
        CORRECTORS.add(new DateCorrector());
        CORRECTORS.add(new RubleCorrector());
    }

    private LineCorrectorUtil() {
    }

    public static String correct(String line) {
        for (LineCorrector corrector : CORRECTORS) {
            line = corrector.correctLine(line);
        }
        return line;
    }
}
