package ua.foxminded.javaspring.formulaoneapplication;

import java.util.Comparator;

public class FormulaOneMain {
    public static void main(String[] args) {
        DataSource<RacerData> racers = new ParsedDataSource<>(new FileReader("abbreviations.txt"), new RacerDataParser());
        DataSource<TimeData> startTime = new ParsedDataSource<>(new FileReader("start.log"), new TimeDataParser());
        DataSource<TimeData> endTime = new ParsedDataSource<>(new FileReader("end.log"), new TimeDataParser());
        DataSource<RacerResult> raceResult = new RaceResult(racers, startTime, endTime);
        DataSource<RacerResult> sortedRaceResult = new SortedDataSource<>(raceResult, Comparator.comparing(RacerResult::getLapTime));
        DataSource<String> racerResultFormatter = new RacerResultFormatter(sortedRaceResult);
        DataSource<String> formatter = new TopRacersFormatter(racerResultFormatter);
        formatter.getData().forEach(System.out::println);
    }
}
