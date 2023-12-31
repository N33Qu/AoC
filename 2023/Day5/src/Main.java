import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader;
        String line;
        StringBuilder seedsLine = new StringBuilder();
        long start = 0;
        int counter = 0;
        long minLocation = 4_000_000_000L;

        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while (!Objects.equals(line = (fileReader.readLine()), "seed-to-soil map:"))
            seedsLine.append(" ").append(line);

        String [] lineSplitted = seedsLine.toString().trim().split(":");
        String [] seeds = lineSplitted[1].trim().split("\\s");



        ArrayList<String> seedToSoil = new ArrayList<>(readLinesUntilMarker(fileReader, "soil-to-fertilizer map:"));
        ArrayList<String> soilToFertilizer = new ArrayList<>(readLinesUntilMarker(fileReader, "fertilizer-to-water map:"));
        ArrayList<String> fertiliserToWater = new ArrayList<>(readLinesUntilMarker(fileReader, "water-to-light map:"));
        ArrayList<String> waterToLight = new ArrayList<>(readLinesUntilMarker(fileReader, "light-to-temperature map:"));
        ArrayList<String> lightToTemperature = new ArrayList<>(readLinesUntilMarker(fileReader, "temperature-to-humidity map:"));
        ArrayList<String> temperatureToHumidity = new ArrayList<>(readLinesUntilMarker(fileReader, "humidity-to-location map:"));
        ArrayList<String> humidityToLocation = new ArrayList<>(readLinesUntilMarker(fileReader, null));


        for (String seed : seeds) {
            if (counter % 2 == 0) {
                start = Long.parseLong(seed);
            }
            if (counter % 2 == 1){
                for (long i = start; i < start + Long.parseLong(seed); i++){

                    long soil = getParamValue(seedToSoil, i);
                    long fertilizer = getParamValue(soilToFertilizer, soil);
                    long water = getParamValue(fertiliserToWater, fertilizer);
                    long light = getParamValue(waterToLight, water);
                    long temperature = getParamValue(lightToTemperature, light);
                    long humidity = getParamValue(temperatureToHumidity, temperature);
                    long location = getParamValue(humidityToLocation, humidity);
                    if (location < minLocation)
                        minLocation = location;
                    //System.out.println("Seed: " + i + "\nSoil: " + soil + "\nFertilizer: " + fertilizer + "\nWater: " + water + "\nLight: " + light
                    // + "\nTemperature: " + temperature + "\nHumidity: " + humidity + "\nLocation: " + location + "\nminLocation: " + minLocation + "\n");
                }
            }
            counter++;

        }

        System.out.println("Minimal value of location: " + minLocation);

    }

    private static long getParam(String line, long seed) {
        if (Objects.equals(line, "") || line == null)
            return 0;
        long destinationStart, sourceStart, range;
        String[] lineArray = line.trim().split("\\s");
        destinationStart = Long.parseLong(lineArray[1]);
        sourceStart = Long.parseLong(lineArray[0]);
        range = Long.parseLong(lineArray[2]);

        for (long i = destinationStart; i < destinationStart + range; i++ ){
            if (seed == i)
                return sourceStart;
            sourceStart++;
        }
        return 0;
    }

    private static List<String> readLinesUntilMarker(BufferedReader reader, String marker) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null && !Objects.equals(line, marker)) {
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }

        return lines;
    }


    private static long getParamValue(List<String> list, long value) {
        long currentValue = 0;
        for (String ele : list) {
            if (currentValue == 0) {
                currentValue = getParam(ele, value);
            } else
                break;
        }
        return (currentValue == 0) ? value : currentValue;
    }


}