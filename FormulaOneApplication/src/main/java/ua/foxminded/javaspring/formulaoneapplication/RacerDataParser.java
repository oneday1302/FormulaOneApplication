package ua.foxminded.javaspring.formulaoneapplication;

public class RacerDataParser implements Parser<RacerData> {

    @Override
    public RacerData parse(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }       
        String[] dataArray = data.split("_");
        if (dataArray.length != 3) {
            throw new IllegalStateException();
        }
        return new RacerData(dataArray[0], dataArray[1], dataArray[2]);
    }
}
