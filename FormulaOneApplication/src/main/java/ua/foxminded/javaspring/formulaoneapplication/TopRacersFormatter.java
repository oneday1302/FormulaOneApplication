package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TopRacersFormatter implements DataSource<String> {
    private static final int DEFAULT_TOP_RACERS = 15;
    private static final char DELIMITER = '-';
    private final AtomicInteger index = new AtomicInteger(1);
    private final DataSource<String> racerResult;
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
        return racerResult.getData()
                          .flatMap(r -> {
                              if (index.getAndIncrement() == topN) {
                                  return Stream.of(r, makeDelimiter(r.length()));
                              }
                              return Stream.of(r);
                          });
    }

    private String makeDelimiter(int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, DELIMITER);
        return new String(chars);
    }
}
