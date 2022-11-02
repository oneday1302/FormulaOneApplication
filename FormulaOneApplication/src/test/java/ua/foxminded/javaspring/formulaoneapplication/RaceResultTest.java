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

class RaceResultTest {

    @Test
    void RaceResult_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        DataSource<TimeData> mockDataSourceTimeData = Mockito.mock(DataSource.class);
        assertThrows(IllegalArgumentException.class, () -> {
            RaceResult raceResult = new RaceResult(null, mockDataSourceTimeData, mockDataSourceTimeData);
        });
    }

    @Test
    void RaceResult_shouldReturnIllegalArgumentException_whenInputSecondParamNull() {
        DataSource<RacerData> mockDataSourceRacerData = Mockito.mock(DataSource.class);
        DataSource<TimeData> mockDataSourceTimeData = Mockito.mock(DataSource.class);
        assertThrows(IllegalArgumentException.class, () -> {
            RaceResult raceResult = new RaceResult(mockDataSourceRacerData, null, mockDataSourceTimeData);
        });
    }

    @Test
    void RaceResult_shouldReturnIllegalArgumentException_whenInputThirdParamNull() {
        DataSource<RacerData> mockDataSourceRacerData = Mockito.mock(DataSource.class);
        DataSource<TimeData> mockDataSourceTimeData = Mockito.mock(DataSource.class);
        assertThrows(IllegalArgumentException.class, () -> {
            RaceResult raceResult = new RaceResult(mockDataSourceRacerData, mockDataSourceTimeData, null);
        });
    }

    @Test
    void RaceResult_shouldReturnIllegalArgumentException_whenInputAllParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            RaceResult raceResult = new RaceResult(null, null, null);
        });
    }

    @Test
    void getData_shouldReturnStreamRacerResult_whenInputNormal() {
        Stream<RacerData> streamRacerData = Stream
                .of(new RacerData("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
        DataSource<RacerData> mockDataSourceRacerData = Mockito.mock(DataSource.class);
        when(mockDataSourceRacerData.getData()).thenReturn(streamRacerData);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

        Stream<TimeData> streamStartTimeData = Stream
                .of(new TimeData("DRR", LocalDateTime.parse("2018-05-24_12:14:12.054", format)));
        DataSource<TimeData> mockDataSourceStartTimeData = Mockito.mock(DataSource.class);
        when(mockDataSourceStartTimeData.getData()).thenReturn(streamStartTimeData);

        Stream<TimeData> streamEndTimeData = Stream
                .of(new TimeData("DRR", LocalDateTime.parse("2018-05-24_12:15:24.067", format)));
        DataSource<TimeData> mockDataSourceEndTimeData = Mockito.mock(DataSource.class);
        when(mockDataSourceEndTimeData.getData()).thenReturn(streamEndTimeData);

        List<RacerResult> expected = new ArrayList<>();
        expected.add(new RacerResult(
                "Daniel Ricciardo", 
                "RED BULL RACING TAG HEUER",
                Duration.between(
                        LocalDateTime.parse("2018-05-24_12:14:12.054", format),
                        LocalDateTime.parse("2018-05-24_12:15:24.067", format))));

        RaceResult raceResult = new RaceResult(mockDataSourceRacerData, mockDataSourceStartTimeData, mockDataSourceEndTimeData);
        List<RacerResult> actual = raceResult.getData().collect(Collectors.toList());
        
        assertEquals(expected, actual);
    }
}
