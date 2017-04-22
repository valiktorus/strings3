package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LineCorrector {

    public String correctLine(String line){
        Matcher matcher = getPattern().matcher(line);
        if (!matcher.find()){
            return line;
        }
        return line.replace(getReplacedPart(matcher), getFixedString(matcher));
    }

    protected abstract String getReplacedPart(Matcher matcher);

    protected abstract Pattern getPattern();

    protected abstract String getFixedString(Matcher matcher);
}