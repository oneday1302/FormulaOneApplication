package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopRacersFormatter implements DataSource<String> {
    private static final int DEFAULT_TOP_RACERS = 15;
    private static final char DELIMITER = '-';
    private List<String> racerResult;
    private final int topN;

    public TopRacersFormatter(DataSource<String> racerResult) {
        this(racerResult, DEFAULT_TOP_RACERS);
    }

    public TopRacersFormatter(DataSource<String> racerResult, int topN) {
        if (racerResult == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.racerResult = racerResult.getData().collect(Collectors.toList());
        if (topN >= this.racerResult.size() || topN <= 0) {
            throw new IllegalStateException();
        }
        this.topN = topN;
    }

    @Override
    public Stream<String> getData() {
        racerResult.add(topN, makeDelimiter(racerResult.get(topN).length()));
        return racerResult.stream();
    }

    private String makeDelimiter(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, DELIMITER);
        return new String(chars);
    }
}
