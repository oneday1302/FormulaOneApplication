package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TopRacersFormatterTest {
    @Test
    void TopRacersFormatter_shouldReturnIllegalArgumentException_whenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            TopRacersFormatter topRacersFormatter = new TopRacersFormatter(null);
        });
    }

    @Test
    void TopRacersFormatter_shouldReturnIllegalStateException_whenInputTopNLessThanZero() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        
        Stream<String> stream = Stream.of(
                "1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                "2.Sebastian Vettel  |FERRARI                   |01:12.415",
                "3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                "4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockDataSource, -1);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
    
    @Test
    void TopRacersFormatter_shouldReturnIllegalStateException_whenInputTopNEqualsZero() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        
        Stream<String> stream = Stream.of(
                "1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                "2.Sebastian Vettel  |FERRARI                   |01:12.415",
                "3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                "4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockDataSource, 0);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
    
    @Test
    void TopRacersFormatter_shouldReturnIllegalStateException_whenInputTopNEqualsNumberOfRacers() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        
        Stream<String> stream = Stream.of(
                "1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                "2.Sebastian Vettel  |FERRARI                   |01:12.415",
                "3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                "4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockDataSource, 4);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
    
    @Test
    void TopRacersFormatter_shouldReturnIllegalStateException_whenInputTopNMoreThanNumberOfRacers() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        
        Stream<String> stream = Stream.of(
                "1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                "2.Sebastian Vettel  |FERRARI                   |01:12.415",
                "3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                "4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockDataSource, 5);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    void format_shouldReturnStringTopThree_whenInputSecondParamThree() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("---------------------------------------------------------");
        expected.add("4.Lewis Hamilton    |MERCEDES                  |01:12.460");

        Stream<String> stream = Stream.of(
                "1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                "2.Sebastian Vettel  |FERRARI                   |01:12.415",
                "3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                "4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockDataSource, 3);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}
