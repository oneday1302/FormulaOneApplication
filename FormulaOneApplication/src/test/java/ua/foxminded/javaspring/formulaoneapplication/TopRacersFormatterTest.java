package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TopRacersFormatterTest {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    Duration timeParse(String startTime, String endTime) {
        return Duration.between(LocalDateTime.parse(startTime, format), LocalDateTime.parse(endTime, format));
    }

    @Test
    void TopRacersFormatter_shouldReturnIllegalArgumentException_whenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            TopRacersFormatter topRacersFormatter = new TopRacersFormatter(null);
        });
    }

    @Test
    void format_shouldReturnStringTopThree_whenInputSecondParamThree() {
        List<String> expected = new ArrayList<>();
        expected.add(" 1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add(" 2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add(" 3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("----------------------------------------------------------");
        expected.add(" 4.Lewis Hamilton    |MERCEDES                  |01:12.460");

        Stream<String> stream = Stream.of(" 1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                " 2.Sebastian Vettel  |FERRARI                   |01:12.415",
                " 3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                " 4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        DataSource<String> mockRacerResult = Mockito.mock(DataSource.class);
        when(mockRacerResult.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockRacerResult, 3);
        List<String> actual = formatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    void format_shouldReturnStringTopFifteen_whenInputSecondParamDefault() {
        List<String> expected = new ArrayList<>();
        expected.add(" 1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add(" 2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add(" 3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add(" 4.Lewis Hamilton    |MERCEDES                  |01:12.460");
        expected.add(" 5.Stoffel Vandoorne |MCLAREN RENAULT           |01:12.463");
        expected.add(" 6.Kimi Raikkonen    |FERRARI                   |01:12.639");
        expected.add(" 7.Fernando Alonso   |MCLAREN RENAULT           |01:12.657");
        expected.add(" 8.Sergey Sirotkin   |WILLIAMS MERCEDES         |01:12.706");
        expected.add(" 9.Charles Leclerc   |SAUBER FERRARI            |01:12.829");
        expected.add("10.Sergio Perez      |FORCE INDIA MERCEDES      |01:12.848");
        expected.add("11.Romain Grosjean   |HAAS FERRARI              |01:12.930");
        expected.add("12.Pierre Gasly      |SCUDERIA TORO ROSSO HONDA |01:12.941");
        expected.add("13.Carlos Sainz      |RENAULT                   |01:12.950");
        expected.add("14.Esteban Ocon      |FORCE INDIA MERCEDES      |01:13.028");
        expected.add("15.Nico Hulkenberg   |RENAULT                   |01:13.065");
        expected.add("----------------------------------------------------------");
        expected.add("16.Brendon Hartley   |SCUDERIA TORO ROSSO HONDA |01:13.179");

        Stream<String> stream = Stream.of(
                " 1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013",
                " 2.Sebastian Vettel  |FERRARI                   |01:12.415",
                " 3.Valtteri Bottas   |MERCEDES                  |01:12.434",
                " 4.Lewis Hamilton    |MERCEDES                  |01:12.460",
                " 5.Stoffel Vandoorne |MCLAREN RENAULT           |01:12.463",
                " 6.Kimi Raikkonen    |FERRARI                   |01:12.639",
                " 7.Fernando Alonso   |MCLAREN RENAULT           |01:12.657",
                " 8.Sergey Sirotkin   |WILLIAMS MERCEDES         |01:12.706",
                " 9.Charles Leclerc   |SAUBER FERRARI            |01:12.829",
                "10.Sergio Perez      |FORCE INDIA MERCEDES      |01:12.848",
                "11.Romain Grosjean   |HAAS FERRARI              |01:12.930",
                "12.Pierre Gasly      |SCUDERIA TORO ROSSO HONDA |01:12.941",
                "13.Carlos Sainz      |RENAULT                   |01:12.950",
                "14.Esteban Ocon      |FORCE INDIA MERCEDES      |01:13.028",
                "15.Nico Hulkenberg   |RENAULT                   |01:13.065",
                "16.Brendon Hartley   |SCUDERIA TORO ROSSO HONDA |01:13.179");
        DataSource<String> mockRacerResult = Mockito.mock(DataSource.class);
        when(mockRacerResult.getData()).thenReturn(stream);
        TopRacersFormatter formatter = new TopRacersFormatter(mockRacerResult);
        List<String> actual = formatter.getData().collect(Collectors.toList());
        
        assertEquals(expected, actual);
    }
}
