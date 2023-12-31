import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader;
        String line;
        Map<String, Integer> scoresMap = new HashMap<>();
        List<Integer> powersList = new ArrayList<>();

        int sum = 0;
        int powSum = 0;
        int correctDraw = 0;
        int gameID;
        int maxRed =  0;
        int maxBlue =  0;
        int maxGreen =  0;

        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while ((line = (fileReader.readLine())) != null) {
            String patternToMatch = "Game (\\d+): ([\\d\\s\\w,;]+)";
            Pattern numberAndColorMatch = Pattern.compile("(\\d+) (\\w+)");
            Pattern pattern = Pattern.compile(patternToMatch);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                gameID = Integer.parseInt(matcher.group(1));
                String setsString = matcher.group(2);
                String[] sets = setsString.split(";");
                for (String set : sets){
                    String[] cubeCountsAndColors = set.trim().split(",");
                    for (String cubeAndColor : cubeCountsAndColors) {
                        Matcher cubeAndColorMatcher = numberAndColorMatch.matcher(cubeAndColor);
                        while (cubeAndColorMatcher.find()){
                            scoresMap.put(cubeAndColorMatcher.group(2), Integer.parseInt(cubeAndColorMatcher.group(1)));
                        }
                    }
                    int red = scoresMap.getOrDefault("red", 0);
                    int blue = scoresMap.getOrDefault("blue", 0);
                    int green = scoresMap.getOrDefault("green", 0);

                    if (maxRed < red){
                        maxRed = red;
                    }
                    if (maxBlue < blue){
                        maxBlue = blue;
                    }
                    if (maxGreen < green){
                        maxGreen = green;
                    }

                    if (scoresMap.getOrDefault("red", 0) > 12 || scoresMap.getOrDefault("green", 0) > 13
                            || scoresMap.getOrDefault("blue", 0) > 14) {
                        scoresMap.clear();
                        continue;
                    }
                    correctDraw++;
                    scoresMap.clear();
                }
                powersList.add((maxRed*maxBlue*maxGreen));
                maxRed = 0;
                maxBlue = 0;
                maxGreen = 0;
                if (correctDraw == sets.length) {
                    sum += gameID;
                }
                correctDraw = 0;


            }
        }
        for (int pow : powersList){
            powSum += pow;
        }
        System.out.println(sum);
        System.out.println(powSum);
    }
}