import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int min = 1;
        int max = 100;
        int generatedNumber = r.nextInt(max - min + 1) + min;
        int attempts = 0;
        int pnts = 100;
        boolean correct = false;
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nWelcome to Guess the Number Game!");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");        
        System.out.println("\nI'm thinking of a number between " + min + " and " + max +" ,\nCan u guess it?");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        while (!correct) {
            System.out.print("\nYour points : " + pnts);            
            System.out.print("\n\nEnter your guess: ");
            int guess = sc.nextInt();
            attempts++;

            if (guess == generatedNumber) {
                System.out.println("\nCongratulations! You guessed the number in " + attempts + " attempts!\nWell done!");
                System.out.println("Your final points : " + pnts);                
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                correct = true;
            } else if (Math.abs(guess - generatedNumber) <= 10){
                System.out.println("\nYou are really close to the number!");
                if (guess < generatedNumber){
                    System.out.println("\nGo a little higher...Perhaps in a range of 10 numbers");    
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                } else {
                    System.out.println("\nGo a little lower...Perhaps in a range of 10 numbers");                                        
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");                    
                }
                pnts -= 5;
            } else if (guess < generatedNumber) {
                System.out.println("That was too low! Try again with a higher number.");
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");     
                pnts -= 10;                
            } else {
                System.out.println("That was too high! Try again with a lower number.");
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");                
                pnts -= 10;                                
            }
        }
        sc.close();
    }
}