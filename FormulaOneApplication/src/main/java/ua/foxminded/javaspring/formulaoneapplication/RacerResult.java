package ua.foxminded.javaspring.formulaoneapplication;

import java.time.Duration;
import java.util.Objects;

public class RacerResult {
    private final String name;
    private final String team;
    private final Duration lapTime;

    public RacerResult(String name, String team, Duration lapTime) {
        if (name == null || team == null || lapTime == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.name = name;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lapTime, name, team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacerResult other = (RacerResult) obj;
        return Objects.equals(lapTime, other.lapTime) && Objects.equals(name, other.name)
                && Objects.equals(team, other.team);
    }
}
