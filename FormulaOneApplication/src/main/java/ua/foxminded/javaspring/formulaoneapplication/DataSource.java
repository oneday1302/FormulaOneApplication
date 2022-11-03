package ua.foxminded.javaspring.formulaoneapplication;

import java.util.stream.Stream;

public interface DataSource<T> {
    Stream<T> getData();
}
