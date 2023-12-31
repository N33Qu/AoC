import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader fileReader;
        String line;
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d|one|two|three|four|five|six|seven|eight|nine", Pattern.CASE_INSENSITIVE);
        int end = 0;
        int sum = 0;
        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while ((line = (fileReader.readLine())) != null) {

            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                stringList.add(converWrittenToNumber(matcher.group()));
                end = matcher.end() - 1;
            }
            if (matcher.find(end)){
                stringList.add(converWrittenToNumber(matcher.group()));
            }
            intList.add(Integer.valueOf(stringList.get(0) + stringList.get(stringList.size() - 1)));
            stringList.clear();
        }

        for (int num : intList)
            sum += num;

        System.out.println(sum);




    }

    public static String converWrittenToNumber(String word){
        return switch (word) {
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            case "nine" ->"9";
            default -> word;
        };
    }
}

