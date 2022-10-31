package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class FileReaderTest {

    @Test
    void FileReader_shouldReturnIllegalArgumentException_whenInputNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FileReader(null);
        });
    }

    @Test
    void getData_shouldReturnNullPointerException_whenInputNonExistingFileName() {
        FileReader fileReader = new FileReader("abc.txt");
        assertThrows(NullPointerException.class, () -> {
            fileReader.getData();
        });
    }

    @Test
    void getData_shouldReturnStream_whenInputCorrectFileName() {
        List<String> expected = new ArrayList<>();
        expected.add("Hello");
        expected.add("World");

        List<String> actual = new FileReader("test.txt").getData().collect(Collectors.toList());
        assertEquals(expected, actual);
    }
}
