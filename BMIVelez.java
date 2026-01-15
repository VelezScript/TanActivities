package MyFirstGUI;

import java.util.Scanner;

public class BMIVelez {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Ask for weight in kg
        System.out.print("Enter your weight (kg): ");
        double weight = input.nextDouble();

        // Ask for height in cm
        System.out.print("Enter your height (cm): ");
        double heightCm = input.nextDouble();

        // Convert height to meters
        double heightM = heightCm / 100;

        // Calculate BMI
        double bmi = weight / (heightM * heightM);

        // Display BMI
        System.out.printf("Your BMI is: %.2f\n", bmi);

        // Determine BMI category
        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25) {
            System.out.println("Category: Normal weight");
        } else if (bmi < 30) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obese");
        }

        input.close();
    }
}
