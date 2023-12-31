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
        String[][] input = new String[141][141];


        Pattern numbersPattern = Pattern.compile("\\b\\d+\\b");
        Pattern signsPattern = Pattern.compile("[/*+\\-@#$%&=]");


        int sum = 0;
        int counter = 0;
        try {
            fileReader = new BufferedReader(new FileReader("text.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 141; i++) {
            for (int j = 0; j < 141; j++) {
                input[i][j] = ".";
            }
        }

        while ((line = (fileReader.readLine())) != null) {
            Matcher numberMatcher = numbersPattern.matcher(line);
            Matcher signMatcher = signsPattern.matcher(line);
            while (numberMatcher.find()) {
                String find = numberMatcher.group();
                for (int i = numberMatcher.end() - find.length() + 1; i <= numberMatcher.end(); i++)
                    input[counter][i] = find;
            }
            while (signMatcher.find()) {
                input[counter][signMatcher.end()] = signMatcher.group();
            }
            counter++;
        }

        for (int i = 0; i < 141; i++) {
            for (int j = 0; j < 141; j++) {
                sum += getNumbers(input, i, j);
            }
        }

        System.out.println(sum);

    }


    public static int getNumbers(String[][] tab, int row, int col) {
        String signs = "[/+\\-@#$%&=]";
        String numbers = "\\b[1-9]\\d*\\b";
        /*if (tab[row][col].matches(signs)) {
            if (row == 0 && col == 0)
                return checkAdjacencyOfAnySign(tab, row, row + 1, col, col + 1, numbers);
            else if (row == 0 && col != 140)
                return checkAdjacencyOfAnySign(tab, row, row + 1, col - 1, col + 1, numbers);
            else if (row == 0)
                return checkAdjacencyOfAnySign(tab, row, row + 1, col - 1, col, numbers);
            else if (row != 140 && col == 0)
                return checkAdjacencyOfAnySign(tab, row - 1, row + 1, col, col + 1, numbers);
            else if (row != 140 && col == 140)
                return checkAdjacencyOfAnySign(tab, row - 1, row + 1, col - 1, col, numbers);
            else if (row == 140 && col != 0 && col != 140)
                return checkAdjacencyOfAnySign(tab, row - 1, row, col - 1, col + 1, numbers);
            else if (row == 140 && col == 0)
                return checkAdjacencyOfAnySign(tab, row - 1, row, col, col + 1, numbers);
            else if (row == 140)
                return checkAdjacencyOfAnySign(tab, row - 1, row, col - 1, col, numbers);
            else
                return checkAdjacencyOfAnySign(tab, row - 1, row + 1, col - 1, col + 1, numbers);
        }else */if (tab[row][col].matches("[*]")) {
            if (row == 0 && col == 0)
                return checkAdjacencyOfAsterisk(tab, row, row + 1, col, col + 1, numbers);
            else if (row == 0 && col != 140)
                return checkAdjacencyOfAsterisk(tab, row, row + 1, col - 1, col + 1, numbers);
            else if (row == 0)
                return checkAdjacencyOfAsterisk(tab, row, row + 1, col - 1, col, numbers);
            else if (row != 140 && col == 0)
                return checkAdjacencyOfAsterisk(tab, row - 1, row + 1, col, col + 1, numbers);
            else if (row != 140 && col == 140)
                return checkAdjacencyOfAsterisk(tab, row - 1, row + 1, col - 1, col, numbers);
            else if (row == 140 && col != 0 && col != 140)
                return checkAdjacencyOfAsterisk(tab, row - 1, row, col - 1, col + 1, numbers);
            else if (row == 140 && col == 0)
                return checkAdjacencyOfAsterisk(tab, row - 1, row, col, col + 1, numbers);
            else if (row == 140)
                return checkAdjacencyOfAsterisk(tab, row - 1, row, col - 1, col, numbers);
            else
                return checkAdjacencyOfAsterisk(tab, row - 1, row + 1, col - 1, col + 1, numbers);
        }
        return 0;
    }


    public static int checkAdjacencyOfAnySign(String[][] tab, int rowStart, int rowEnd, int colStart, int colEnd, String regex) {

        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                if (tab[i][j].matches(regex) && !tab[i][j].equals("0"))
                    return Integer.parseInt(tab[i][j]);
            }
        }
        return 0;

    }


    public static int checkAdjacencyOfAsterisk(String[][] tab, int rowStart, int rowEnd, int colStart, int colEnd, String regex) {
        int num1 = 0;
        int num2 = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                if (tab[i][j].matches(regex)) {
                    if (num1 == 0)
                        num1 = Integer.parseInt(tab[i][j]);
                    else
                        num2 = Integer.parseInt(tab[i][j]);
                }
            }
        }
        if (num1 == num2)
            return 0;

        return num1 * num2;
    }
}