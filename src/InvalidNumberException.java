import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class MyClass {
    private MyClass() {}

    public static int[] readIntegersFromFile(String filename) throws IOException, InvalidNumberException {
        int[] array = new int[10];
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line);
                    array[index++] = num;
                } catch (NumberFormatException e) {
                    throw new InvalidNumberException("Invalid number in file: " + line);
                }
            }
        }
        return array;
    }

    public static int sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        try {
            int[] array = MyClass.readIntegersFromFile("input.txt");
            int sum = MyClass.sumArray(array);
            System.out.println("Sum: " + sum);
        } catch (IOException | InvalidNumberException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error: " + e.getMessage());
        }
    }
}

