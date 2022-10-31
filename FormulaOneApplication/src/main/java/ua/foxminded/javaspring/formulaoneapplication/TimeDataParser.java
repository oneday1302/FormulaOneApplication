package ua.foxminded.javaspring.formulaoneapplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDataParser implements Parser<TimeData> {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final int ABBREVIATION_LENGTH = 3;

    @Override
    public TimeData parse(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return new TimeData(data.substring(0, ABBREVIATION_LENGTH),
                LocalDateTime.parse(data.substring(ABBREVIATION_LENGTH), format));
    }
}
