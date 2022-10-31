package ua.foxminded.javaspring.formulaoneapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RacerDataParserTest {

    @Test
    void parse_shouldReturnIllegalArgumentException_whenInputNull() {
        RacerDataParser racerDataParser = new RacerDataParser();
        assertThrows(IllegalArgumentException.class, () -> {
            racerDataParser.parse(null);
        });
    }

    @Test
    void parse_shouldReturnIllegalStateException_whenInputEmptyString() {
        RacerDataParser racerDataParser = new RacerDataParser();
        assertThrows(IllegalStateException.class, () -> {
            racerDataParser.parse("");
        });
    }

    @Test
    void parse_shouldReturnIllegalStateException_whenInputWrongFormatString() {
        RacerDataParser racerDataParser = new RacerDataParser();
        assertThrows(IllegalStateException.class, () -> {
            racerDataParser.parse("abc");
        });
    }

    @Test
    void parse_shouldReturnRacerData_whenInputCorrectFormatString() {
        RacerData expected = new RacerData("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        RacerData actual = new RacerDataParser().parse("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        assertEquals(expected, actual);
    }
}
