package ua.foxminded.javaspring.formulaoneapplication;

import java.util.stream.Stream;

public interface DataSource<T> {
    public Stream<T> getData();
}
