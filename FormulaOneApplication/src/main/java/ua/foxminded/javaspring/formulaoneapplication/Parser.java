package ua.foxminded.javaspring.formulaoneapplication;

public interface Parser<T> {
    T parse(String data);
}
