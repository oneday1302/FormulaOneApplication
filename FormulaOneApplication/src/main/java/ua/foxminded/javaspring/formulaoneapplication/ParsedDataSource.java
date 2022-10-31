package ua.foxminded.javaspring.formulaoneapplication;

import java.util.stream.Stream;

public class ParsedDataSource<T> implements DataSource<T> {
    private final DataSource<String> dataSource;
    private final Parser<T> parser;

    ParsedDataSource(DataSource<String> dataSource, Parser<T> parser) {
        if (dataSource == null || parser == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.dataSource = dataSource;
        this.parser = parser;
    }

    @Override
    public Stream<T> getData() {
        return dataSource.getData().map(parser::parse);
    }
}
