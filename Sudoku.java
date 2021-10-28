package sudoku;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * Driver for the SudokuPuzzle class
 * Allows user to play Sudoku
 * @author CS 115, Anthony Clavette
 * @version 4-23-2020
 */
public class Sudoku 
{
    public static void main(String[] args) {
        PuzzlePool.loadFiles(Paths.get(".\\Data"));
        PuzzlePool.getValidPuzzles();
        SudokuPuzzle puzzle = PuzzlePool.getRandomPuzzle();
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Sudoku Game: ");
       
        boolean done = false;        
        System.out.println("The randomly generated valid puzzle is: \n" + puzzle);
        
        while(!done){
            System.out.println("What would you like to do? \n" +
                    "Clear Puzzle(C) Set a square (S) Get possible values (G) Quit (Q) ");
            String response = reader.next();
            response = response.toLowerCase();
            
            switch (response) {
                case "q":
                    {
                        System.out.println("Thanks for playing.");
                        done = true;
                        break;
                    }
                case "s":
                    {
                        
                        System.out.println("Which row (1-9) and column (1-9) do you want to change?");
                        int row = reader.nextInt()-1;
                        int col = reader.nextInt()-1;
                        System.out.println("What should the value (1-9) be?");
                        try{
                        int value = reader.nextInt();
                        puzzle.addGuess(row, col, value);
                        }
                        catch(InvalidCharacterException e)
                        {
                            System.out.println("Invalid input, integer must be 1-9.");
                        }
                        catch(InputMismatchException e)
                        {
                            System.out.println("Not a valid character. Please enter an integer 1-9.");
                        }                        
                        break;
                    }
                case "g":
                    {
                        System.out.println("Which row (1-9) and column (1-9) do you want to get values for?");
                        int row = reader.nextInt()-1;
                        int col = reader.nextInt()-1;
                        boolean valid[] = puzzle.getAllowedValues(row, col);
                        System.out.print("Allowed values are: ");
                        for(int i=0; i<9; i++){
                            if(valid[i])  //if the array member contains a true
                                System.out.print((i+1)+ "  ");      //then print the digit 1 up from it
                        }   
                        System.out.println();
                        break;
                    }
                case "c":
                    {
                        puzzle.reset();
                        puzzle = PuzzlePool.getRandomPuzzle();
                        break;
                    }
                default:
                    break;
            }
            
            System.out.print("The puzzle is now: \n" + puzzle);
            if(!puzzle.checkPuzzle())
                System.out.println("You have made an ERROR in the puzzle.");
            else if(puzzle.isFull())
                System.out.println("Congratulations, you have completed the puzzle.");           
        }
    }
}