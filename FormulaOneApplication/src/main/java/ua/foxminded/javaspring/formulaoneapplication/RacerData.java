package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Objects;

public class RacerData {
    private final String abbreviation;
    private final String name;
    private final String team;

    public RacerData(String abbreviation, String name, String team) {
        if (abbreviation == null || name == null || team == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, name, team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacerData other = (RacerData) obj;
        return Objects.equals(abbreviation, other.abbreviation) && Objects.equals(name, other.name)
                && Objects.equals(team, other.team);
    }
}
