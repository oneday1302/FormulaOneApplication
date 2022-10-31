package ua.foxminded.javaspring.formulaoneapplication;

public interface Parser<T> {
    public T parse(String data);
}
