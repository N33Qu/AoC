import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map <Integer, Integer> values = new HashMap<>();
        values = getParams();
    }


    public static Map <Integer, Integer> getParams() throws IOException {
        Map <Integer, Integer> paramMap = new HashMap<>();
        BufferedReader fileReader;
        String line;


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

        for (int i = 0; i < times.length - 1; i++) {
            paramMap.put(Integer.parseInt(times[i]), Integer.parseInt(distances[i]));
        }
        return paramMap;
    }

    private static String [] getValues(String line) {
        return line.trim().split(":");
    }

}