package ua.foxminded.javaspring.formulaoneapplication;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeData {
    private final String recerAbbreviation;
    private final LocalDateTime time;

    public TimeData(String recerAbbreviation, LocalDateTime time) {
        if (recerAbbreviation == null || time == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.recerAbbreviation = recerAbbreviation;
        this.time = time;
    }

    public String getRecerAbbreviation() {
        return recerAbbreviation;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, recerAbbreviation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeData other = (TimeData) obj;
        return Objects.equals(time, other.time) && Objects.equals(recerAbbreviation, other.recerAbbreviation);
    }
}
