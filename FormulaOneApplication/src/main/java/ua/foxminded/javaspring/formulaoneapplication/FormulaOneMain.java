package ua.foxminded.javaspring.formulaoneapplication;

public class FormulaOneMain {
    public static void main(String[] args) {
        DataSource<RacerData> racers = new ParsedDataSource<>(new FileReader("abbreviations.txt"), new RacerDataParser());
        DataSource<TimeData> startTime = new ParsedDataSource<>(new FileReader("start.log"), new TimeDataParser());
        DataSource<TimeData> endTime = new ParsedDataSource<>(new FileReader("end.log"), new TimeDataParser());
        DataSource<RacerResult> raceResult = new RaceResult(racers, startTime, endTime);
        Formatter formatter = new TopRacersFormatter(raceResult);
        System.out.println(formatter.format());
    }
}
