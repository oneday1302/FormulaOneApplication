package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Comparator;
import java.util.stream.Stream;

public class SortedDataSource<T> implements DataSource<T> {
    private final DataSource<T> raceResult;
    private final Comparator<T> comparator;

    public SortedDataSource(DataSource<T> raceResult, Comparator<T> comparator) {
        if (raceResult == null || comparator == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.raceResult = raceResult;
        this.comparator = comparator;
    }

    @Override
    public Stream<T> getData() {
        return raceResult.getData().sorted(comparator);
    }
}
