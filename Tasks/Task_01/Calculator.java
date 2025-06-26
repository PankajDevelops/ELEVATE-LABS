package ElevateLabs.Tasks.Task_01;

import java.util.Scanner;

public class Calculator {

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return Double.NaN;
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing;

        do {
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            double result = 0;
            boolean validOperator = true;

            switch (operator) {
                case '+':
                    result = add(num1, num2);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    break;
                case '/':
                    result = divide(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operator!");
                    validOperator = false;
                    break;
            }

            if (validOperator) {
                if (!Double.isNaN(result)) {
                    System.out.println("The result is: " + result);
                }
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String response = scanner.next();
            keepGoing = response.equalsIgnoreCase("yes");

        } while (keepGoing);

        System.out.println("Calculator is closing. Goodbye!");
        scanner.close();
    }
}
