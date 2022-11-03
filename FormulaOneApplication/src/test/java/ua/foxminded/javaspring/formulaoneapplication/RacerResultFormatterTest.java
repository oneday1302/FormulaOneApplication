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

class RacerResultFormatterTest {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    Duration parseTime(String startTime, String endTime) {
        return Duration.between(LocalDateTime.parse(startTime, format), LocalDateTime.parse(endTime, format));
    }

    @Test
    void RacerResultFormatter_shouldReturnIllegalArgumentException_whenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            RacerResultFormatter racerResultFormatter = new RacerResultFormatter(null);
        });
    }

    @Test
    void getData_shouldReturnStreamString_whenInputNormal() {
        List<String> expected = new ArrayList<>();
        expected.add("1.Daniel Ricciardo |RED BULL RACING TAG HEUER |01:12.013");
        expected.add("2.Sebastian Vettel |FERRARI                   |01:12.415");
        expected.add("3.Valtteri Bottas  |MERCEDES                  |01:12.434");
        expected.add("4.Lewis Hamilton   |MERCEDES                  |01:12.460");

        Stream<RacerResult> stream = Stream.of(
                new RacerResult("Daniel Ricciardo", "RED BULL RACING TAG HEUER",
                        parseTime("2018-05-24_12:14:12.054", "2018-05-24_12:15:24.067")),
                new RacerResult("Sebastian Vettel", "FERRARI",
                        parseTime("2018-05-24_12:02:58.917", "2018-05-24_12:04:11.332")),
                new RacerResult("Valtteri Bottas", "MERCEDES",
                        parseTime("2018-05-24_12:00:00.000", "2018-05-24_12:01:12.434")),
                new RacerResult("Lewis Hamilton", "MERCEDES",
                        parseTime("2018-05-24_12:18:20.125", "2018-05-24_12:19:32.585")));
        DataSource<RacerResult> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        RacerResultFormatter racerResultFormatter = new RacerResultFormatter(mockDataSource);
        List<String> actual = racerResultFormatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}
