import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map <Long, Long> values;
        values = getParams();
        int result = 0;
        for (Map.Entry<Long, Long> value : values.entrySet()){
            if (result == 0)
                result = getNumberOfWins(value.getKey(), value.getValue());
            else
                result *= getNumberOfWins(value.getKey(), value.getValue());
        }

        System.out.println(result);

    }


    public static Map <Long, Long> getParams() throws IOException {
        Map <Long, Long> paramMap = new HashMap<>();
        BufferedReader fileReader;
        String line;
        StringBuilder time = new StringBuilder();
        StringBuilder distance = new StringBuilder();


        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        line = fileReader.readLine();
        String [] times = getValues(line);
        line = fileReader.readLine();
        String [] distances = getValues(line);

        times = times[1].trim().split("\\s+");
        distances = distances[1].trim().split("\\s+");

        for (int i = 0; i < times.length; i++) {
            time.append(times[i]);
            distance.append(distances[i]);
//            paramMap.put(Integer.parseInt(times[i]), Integer.parseInt(distances[i])); //Part 1
        }
        paramMap.put(Long.parseLong(time.toString()), Long.parseLong(distance.toString()));
        return paramMap;
    }

    private static String [] getValues(String line) {
        return line.trim().split(":");
    }

    private static Integer getNumberOfWins(Long time, Long distance){
        int numberOfWins = 0;
        for (int i = 1; i < time; i++) {
            if ((time - i) * i > distance)
                numberOfWins++;
        }
        return numberOfWins;
    }

}