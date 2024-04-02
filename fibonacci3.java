import java.util.Scanner;

public class fibonacci3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Fibonacci elements to be printed:");
        int n = scanner.nextInt();

        System.out.println("Choose output format:");
        System.out.println("1. Numeric");
        System.out.println("2. Words");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        FibonacciGenerator fibonacciGenerator;
        if (choice == 1) {
            fibonacciGenerator = new NumericFibonacciGenerator();
        } else {
            fibonacciGenerator = new WordFibonacciGenerator();
        }

        fibonacciGenerator.generateFibonacci(n);
    }
}

class FibonacciGenerator {
    public void generateFibonacci(int n) {
        int a = 0, b = 1;
        System.out.println("Fibonacci series:");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }
}

class NumericFibonacciGenerator extends FibonacciGenerator {
    @Override
    public void generateFibonacci(int n) {
        super.generateFibonacci(n);
    }
}

class WordFibonacciGenerator extends FibonacciGenerator {
    @Override
    public void generateFibonacci(int n) {
        System.out.println("\nFibonacci series in words:");
        for (int i = 0; i < n; i++) {
            String word = NumberWords.convertNumberToWords(calculateFibonacci(i));
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(word);
        }
    }

    private int calculateFibonacci(int n) {
        if (n <= 1)
            return n;
        int fib = 1;
        int prevFib = 1;
        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }
}

class NumberWords {
    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] teens = {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String convertNumberToWords(int number) {
        if (number == 0) {
            return "Zero";
        }
        return convertNumber(number).trim();
    }

    private static String convertNumber(int number) {
        if (number < 0) {
            return "Negative " + convertNumber(-number);
        }
        if (number < 10) {
            return units[number];
        }
        if (number < 20) {
            return teens[number - 10];
        }
        if (number < 100) {
            return tens[number / 10] + "-" + units[number % 10];
        }
        return "Number too large to convert";
    }
}