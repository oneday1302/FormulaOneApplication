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

class ParsedDataSourceTest {

    @Test
    void ParsedDataSource_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        Parser<String> mockParser = Mockito.mock(Parser.class);
        assertThrows(IllegalArgumentException.class, () -> {
            ParsedDataSource<String> parsedDataSource = new ParsedDataSource<>(null, mockParser);
        });
    }

    @Test
    void ParsedDataSource_shouldReturnIllegalArgumentException_whenInputSecondParamNull() {
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        assertThrows(IllegalArgumentException.class, () -> {
            ParsedDataSource<String> parsedDataSource = new ParsedDataSource<>(mockDataSource, null);
        });
    }

    @Test
    void getData_shouldReturnStream_whenInputNormal() {
        Stream<String> racerData = Stream.of("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(racerData);

        RacerData racer = new RacerData("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        Parser<RacerData> mockParser = Mockito.mock(Parser.class);
        when(mockParser.parse("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER")).thenReturn(racer);

        List<RacerData> expected = new ArrayList<>();
        expected.add(racer);

        ParsedDataSource<RacerData> parsedDataSource = new ParsedDataSource<>(mockDataSource, mockParser);
        List<RacerData> actual = parsedDataSource.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}
