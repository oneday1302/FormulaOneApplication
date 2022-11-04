package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FormattedRacerResultDataSourceTest {

    @Test
    void RacerResultFormatter_shouldReturnIllegalArgumentException_whenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            FormattedRacerResultDataSource racerResultFormatter = new FormattedRacerResultDataSource(null);
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
                new RacerResult("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new RacerResult("Sebastian Vettel", "FERRARI", Duration.parse("PT1M12.415S")),
                new RacerResult("Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")),
                new RacerResult("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.460S")));
        DataSource<RacerResult> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        FormattedRacerResultDataSource racerResultFormatter = new FormattedRacerResultDataSource(mockDataSource);
        List<String> actual = racerResultFormatter.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}
