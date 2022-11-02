package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
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
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add(" 1.Daniel Ricciardo  |RED BULL RACING TAG HEUER |01:12.013");
        expected.add(" 2.Sebastian Vettel  |FERRARI                   |01:12.415");
        expected.add(" 3.Valtteri Bottas   |MERCEDES                  |01:12.434");
        expected.add("----------------------------------------------------------");
        expected.add(" 4.Lewis Hamilton    |MERCEDES                  |01:12.460");

        Stream<RacerResult> streamRacerResult = Stream.of(
                new RacerResult("Daniel Ricciardo", "RED BULL RACING TAG HEUER",
                        timeParse("2018-05-24_12:14:12.054", "2018-05-24_12:15:24.067")),
                new RacerResult("Sebastian Vettel", "FERRARI",
                        timeParse("2018-05-24_12:02:58.917", "2018-05-24_12:04:11.332")),
                new RacerResult("Valtteri Bottas", "MERCEDES",
                        timeParse("2018-05-24_12:00:00.000", "2018-05-24_12:01:12.434")),
                new RacerResult("Lewis Hamilton", "MERCEDES",
                        timeParse("2018-05-24_12:18:20.125", "2018-05-24_12:19:32.585")));
        DataSource<RacerResult> mockRacerResult = Mockito.mock(DataSource.class);
        when(mockRacerResult.getData()).thenReturn(streamRacerResult);
        TopRacersFormatter actual = new TopRacersFormatter(mockRacerResult, 3);

        assertEquals(expected.toString(), actual.format());
    }

    @Test
    void format_shouldReturnStringTopFifteen_whenInputSecondParamDefault() {
        StringJoiner expected = new StringJoiner(System.lineSeparator());
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

        Stream<RacerResult> streamRacerResult = Stream.of(
                new RacerResult("Daniel Ricciardo", "RED BULL RACING TAG HEUER",
                        timeParse("2018-05-24_12:14:12.054", "2018-05-24_12:15:24.067")),
                new RacerResult("Sebastian Vettel", "FERRARI",
                        timeParse("2018-05-24_12:02:58.917", "2018-05-24_12:04:11.332")),
                new RacerResult("Valtteri Bottas", "MERCEDES",
                        timeParse("2018-05-24_12:00:00.000", "2018-05-24_12:01:12.434")),
                new RacerResult("Lewis Hamilton", "MERCEDES",
                        timeParse("2018-05-24_12:18:20.125", "2018-05-24_12:19:32.585")),
                new RacerResult("Stoffel Vandoorne", "MCLAREN RENAULT",
                        timeParse("2018-05-24_12:18:37.735", "2018-05-24_12:19:50.198")),
                new RacerResult("Kimi Raikkonen", "FERRARI",
                        timeParse("2018-05-24_12:03:01.250", "2018-05-24_12:04:13.889")),
                new RacerResult("Fernando Alonso", "MCLAREN RENAULT",
                        timeParse("2018-05-24_12:13:04.512", "2018-05-24_12:14:17.169")),
                new RacerResult("Sergey Sirotkin", "WILLIAMS MERCEDES",
                        timeParse("2018-05-24_12:16:11.648", "2018-05-24_12:17:24.354")),
                new RacerResult("Charles Leclerc", "SAUBER FERRARI",
                        timeParse("2018-05-24_12:09:41.921", "2018-05-24_12:10:54.750")),
                new RacerResult("Sergio Perez", "FORCE INDIA MERCEDES",
                        timeParse("2018-05-24_12:12:01.035", "2018-05-24_12:13:13.883")),
                new RacerResult("Romain Grosjean", "HAAS FERRARI",
                        timeParse("2018-05-24_12:05:14.511", "2018-05-24_12:06:27.441")),
                new RacerResult("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA",
                        timeParse("2018-05-24_12:07:23.645", "2018-05-24_12:08:36.586")),
                new RacerResult("Carlos Sainz", "RENAULT",
                        timeParse("2018-05-24_12:03:15.145", "2018-05-24_12:04:28.095")),
                new RacerResult("Esteban Ocon", "FORCE INDIA MERCEDES",
                        timeParse("2018-05-24_12:17:58.810", "2018-05-24_12:19:11.838")),
                new RacerResult("Nico Hulkenberg", "RENAULT",
                        timeParse("2018-05-24_12:02:49.914", "2018-05-24_12:04:02.979")),
                new RacerResult("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA",
                        timeParse("2018-05-24_12:14:51.985", "2018-05-24_12:16:05.164")));
        DataSource<RacerResult> mockRacerResult = Mockito.mock(DataSource.class);
        when(mockRacerResult.getData()).thenReturn(streamRacerResult);
        TopRacersFormatter actual = new TopRacersFormatter(mockRacerResult);

        assertEquals(expected.toString(), actual.format());
    }
}
