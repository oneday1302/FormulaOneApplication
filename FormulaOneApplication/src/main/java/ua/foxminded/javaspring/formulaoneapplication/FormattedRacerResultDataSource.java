package ua.foxminded.javaspring.formulaoneapplication;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormattedRacerResultDataSource implements DataSource<String> {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");
    private final Stream<RacerResult> racerResult;
    private final AtomicInteger place = new AtomicInteger(1);
    private int maxPlaceLength;
    private int maxNameLength;
    private int maxTeamLength;

    public FormattedRacerResultDataSource(DataSource<RacerResult> racerResult) {
        if (racerResult == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.racerResult = findMaxLength(racerResult);
    }

    @Override
    public Stream<String> getData() {
        String rowFormat = "%" + maxPlaceLength + "d.%-" + maxNameLength + "s |%-" + maxTeamLength + "s |%s";
        return racerResult.map(r -> racerResultToString(r, rowFormat));
    }

    private Stream<RacerResult> findMaxLength(DataSource<RacerResult> racerResult) {
        List<RacerResult> list = racerResult.getData().collect(Collectors.toList());
        maxPlaceLength = String.valueOf(list.size()).length();
        list.forEach(r -> {
            maxNameLength = Math.max(maxNameLength, r.getName().length());
            maxTeamLength = Math.max(maxTeamLength, r.getTeam().length());
        });
        return list.stream();
    }

    private String racerResultToString(RacerResult r, String rowFormat) {
        return String.format(rowFormat, place.getAndIncrement(), r.getName(), r.getTeam(), formatTime(r.getLapTime()));
    }

    private String formatTime(Duration time) {
        return TIME_FORMATTER.format(LocalTime.MIDNIGHT.plus(time));
    }
}