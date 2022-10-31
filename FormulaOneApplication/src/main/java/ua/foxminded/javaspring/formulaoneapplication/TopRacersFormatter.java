package ua.foxminded.javaspring.formulaoneapplication;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TopRacersFormatter implements Formatter {
    private static final int DEFAULT_TOP_RACERS = 15;
    private static final char DELIMITER = '-';
    private static final String ROW_FORMAT = "%2d.%-17s |%-25s |%s";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");
    private final AtomicInteger place = new AtomicInteger();
    private final DataSource<RacerResult> racerResult;
    private final int topN;

    public TopRacersFormatter(DataSource<RacerResult> racerResult) {
        this(racerResult, DEFAULT_TOP_RACERS);
    }

    public TopRacersFormatter(DataSource<RacerResult> racerResult, int topN) {
        if (racerResult == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.racerResult = racerResult;
        this.topN = topN;
    }

    @Override
    public String format() {
        return racerResult.getData()
                          .sorted(Comparator.comparing(RacerResult::getLapTime))
                          .map(this::racerResultToString)
                          .collect(Collectors.joining(System.lineSeparator()));
    }

    private String makeDelimiter(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, DELIMITER);
        return new String(chars);
    }

    private String formatTime(Duration time) {      
        return TIME_FORMATTER.format(LocalTime.MIDNIGHT.plus(time));
    }
    
    private String racerResultToString(RacerResult r) {
        if (place.get() == topN) {
            String row = String.format(ROW_FORMAT, place.incrementAndGet(), r.getName(), r.getTeam(), formatTime(r.getLapTime()));
            return makeDelimiter(row.length()) + "\n" + row;
        }
        return String.format(ROW_FORMAT, place.incrementAndGet(), r.getName(), r.getTeam(), formatTime(r.getLapTime()));
    }
}
