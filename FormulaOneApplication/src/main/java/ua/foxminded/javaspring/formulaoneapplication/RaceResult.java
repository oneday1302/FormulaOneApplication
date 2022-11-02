package ua.foxminded.javaspring.formulaoneapplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RaceResult implements DataSource<RacerResult> {
    private final DataSource<RacerData> racers;
    private final DataSource<TimeData> start;
    private final DataSource<TimeData> end;

    public RaceResult(DataSource<RacerData> racers, DataSource<TimeData> start, DataSource<TimeData> end) {
        if (racers == null || start == null || end == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        this.racers = racers;
        this.start = start;
        this.end = end;
    }

    @Override
    public Stream<RacerResult> getData() {
        return racers.getData().map(makeCombinator(toMap(start), toMap(end)));
    }

    private Map<String, LocalDateTime> toMap(DataSource<TimeData> timeData) {
        return timeData.getData().collect(Collectors.toMap(TimeData::getRecerAbbreviation, TimeData::getTime));
    }

    private Function<RacerData, RacerResult> makeCombinator(Map<String, LocalDateTime> startTimes, Map<String, LocalDateTime> endTimes) {
        return racer -> new RacerResult(
                racer.getName(), 
                racer.getTeam(),
                Duration.between(startTimes.get(racer.getAbbreviation()), endTimes.get(racer.getAbbreviation())));
    }
}
