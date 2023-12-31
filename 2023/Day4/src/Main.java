
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader;
        String line;
        int sum = 0;
        Map<Integer, Integer> linesMap = new HashMap<>();
        int counter = 1;
        linesMap.put(1, 1);
        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        while ((line = (fileReader.readLine())) != null) {
            String[] cardAndNumbers = line.split(":\\s");
            int value = getValue(cardAndNumbers[1]);
            linesMap.putIfAbsent(counter, 1);
            for (int i = 1; i <= value; i++){
                if (counter + i > 203)
                    break;
                if (counter == 1)
                    linesMap.put(counter + i, 1 + linesMap.get(counter));
                else{
                    linesMap.putIfAbsent(counter + i, 1);
                    linesMap.put(counter + i, linesMap.get(counter + i) + linesMap.get(counter));
                }
            }

            counter++;
        }
        for (Map.Entry<Integer, Integer> entry : linesMap.entrySet())
            sum += entry.getValue();

        System.out.println(sum);
    }

    private static int getValue(String cardAndNumbers) {
        String [] winningAndReceivedNumbers = cardAndNumbers.split("\\|");
        String [] winningNumbers = winningAndReceivedNumbers[0].trim().split("\\s+");
        String [] receivedNumbers = winningAndReceivedNumbers[1].trim().split("\\s+");
        int value = 0;
        for (String receivedNumber : receivedNumbers) {
            for (String winningNumber : winningNumbers){
                if (receivedNumber.equals(winningNumber)) {
                    /*if (value == 0)
                        value = 1;
                    else
                        value *= 2;*/
                    value += 1;
                }
            }
        }
        return value;
    }
}