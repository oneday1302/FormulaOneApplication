package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopRacersFormatter implements DataSource<String> {
    private static final int DEFAULT_TOP_RACERS = 15;
    private static final char DELIMITER = '-';
    private DataSource<String> racerResult;
    private final int topN;

    public TopRacersFormatter(DataSource<String> racerResult) {
        this(racerResult, DEFAULT_TOP_RACERS);
    }

    public TopRacersFormatter(DataSource<String> racerResult, int topN) {
        if (racerResult == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.racerResult = racerResult;
        this.topN = topN;
    }

    @Override
    public Stream<String> getData() {
        List<String> list = racerResult.getData().collect(Collectors.toList());
        if (topN <= 0 || topN >= list.size()) {
            return list.stream();
        }
        list.add(topN, makeDelimiter(list.get(topN).length()));
        return list.stream();
    }

    private String makeDelimiter(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, DELIMITER);
        return new String(chars);
    }
}
