package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SortedDataSourceTest {

    @Test
    void SortedDataSource_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        Comparator<String> comparator = Comparator.naturalOrder();
        assertThrows(IllegalArgumentException.class, () -> {
            SortedDataSource<String> sortedDataSource = new SortedDataSource<String>(null, comparator);
        });
    }

    @Test
    void SortedDataSource_shouldReturnIllegalArgumentException_whenInputSecondParamNull() {
        DataSource<String> mockDataSource = Mockito.mock(DataSource.class);
        assertThrows(IllegalArgumentException.class, () -> {
            SortedDataSource<String> sortedDataSource = new SortedDataSource<String>(mockDataSource, null);
        });
    }

    @Test
    void SortedDataSource_shouldReturnIllegalArgumentException_whenInputBothParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            SortedDataSource<String> sortedDataSource = new SortedDataSource<String>(null, null);
        });
    }

    @Test
    void getData_shouldReturnSortedStream_whenInputNormal() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> stream = Stream.of(5, 4, 3, 2, 1);
        DataSource<Integer> mockDataSource = Mockito.mock(DataSource.class);
        when(mockDataSource.getData()).thenReturn(stream);
        SortedDataSource<Integer> sortedDataSource = new SortedDataSource<>(mockDataSource, Comparator.naturalOrder());
        List<Integer> actual = sortedDataSource.getData().collect(Collectors.toList());

        assertEquals(expected, actual);
    }
}
