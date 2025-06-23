import java.util.Scanner;
class numbergame{
    public static void main (String[]args){
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int randomNumber =(int)(Math.random()*101);
        System.out.println("A random number is chosen between 1 and 100");
        int attemptsForGuess = 10;
        System.out.println("You have " + attemptsForGuess + " attempts to guess the number" );
        for(int i=1; i <= attemptsForGuess; i++){
           System.out.println("Enter the guess number: ");
           int guessNumber = scanner.nextInt();
        if(randomNumber > guessNumber){
            System.out.println("Guess is too low. Try again.");
            score -=2;
            System.out.println("Current Score:" + score);
            System.out.println("Attempts:"+ i);
        }else if(randomNumber < guessNumber){
            System.out.println("Guess is too high. Try again.");
            System.out.println("Current Score:" + score);
            System.out.println("Attempts:"+ i);
        }else if(randomNumber == guessNumber){
            score+=20;
            System.out.println("Congratulations!.Your guess is correct.");
            System.out.println("Game Over! The number was,"+ randomNumber);
            System.out.println("Displaying the score:");
            System.out.println("Your Final Score:" + score);
            System.out.println("Attempts used:"+ i);
            scanner.close();
            return;
        }    
    }
    System.out.println("Displaying the score:");
    System.out.println("Game Over! The number was,"+ randomNumber);
    System.out.println("Your Final Score:" + score);
    scanner.close();
    }
        }

        
        


        

