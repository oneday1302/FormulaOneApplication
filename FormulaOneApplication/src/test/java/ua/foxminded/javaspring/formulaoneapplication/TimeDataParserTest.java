package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class TimeDataParserTest {

    @Test
    void parse_shouldReturnIllegalArgumentException_whenInputNull() {
        TimeDataParser timeDataParser = new TimeDataParser();
        assertThrows(IllegalArgumentException.class, () -> {
            timeDataParser.parse(null);
        });
    }

    @Test
    void parse_shouldReturnIllegalStateException_whenInputEmptyString() {
        TimeDataParser timeDataParser = new TimeDataParser();
        assertThrows(IllegalStateException.class, () -> {
            timeDataParser.parse("");
        });
    }

    @Test
    void parse_shouldReturnIllegalStateException_whenInputStringLengthLessThanFour() {
        TimeDataParser timeDataParser = new TimeDataParser();
        assertThrows(IllegalStateException.class, () -> {
            timeDataParser.parse("abc");
        });
    }

    @Test
    void parse_shouldReturnTimeData_whenInputCorrectFormatString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        TimeData expected = new TimeData("MES", LocalDateTime.parse("2018-05-24_12:05:58.778", format));
        TimeData actual = new TimeDataParser().parse("MES2018-05-24_12:05:58.778");
        assertEquals(expected, actual);
    }
}
