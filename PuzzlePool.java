package sudoku;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
/**
 * Reads Data folder and adds properly formatted puzzles from the folder to 
 * a pool of puzzles to choose from.
 * @author Anthony Clavette
 * @version 4-23-2020
 */
public class PuzzlePool {
   public static Stack<SudokuPuzzle> puzzles = new Stack();
   private static File[] files = new File[10];
   private static int count = 0;
   
   /**
    * Loads the files from the folder into an array recursively. 
    * @param path the current file path
    */
   public static void loadFiles(Path path)
   {
        File[] tempFiles;
        File file1;       
        file1 = new File(path.toString());
        tempFiles = file1.listFiles();        
        for(int i=0; i<tempFiles.length; i++)
        {
            File file = tempFiles[i];
            if(file.isFile())                      
                files[count++] = file;
            else if (file.isDirectory())      
                loadFiles(Paths.get(file.toString()));
        }       
   }
   static int valid = 0;
   static int invalid = 0;
   final static int total = files.length;
   
   /**
    * Adds the properly formatted puzzles to a Stack of SudokuPuzzle objects
    * and removes the improperly formatted ones. Also counts how many total, 
    * valid, and invalid puzzles there are and prints the amounts.
    */
   public static void getValidPuzzles()
   {
       for(int i=0; i<files.length; i++)
       {
           puzzles.push(new SudokuPuzzle(9, files[i].toString()));
            try
            {        
                puzzles.get(puzzles.size()-1).initializePuzzle();
            }
            catch (InvalidFileFormatException e)
            {
                puzzles.remove(puzzles.size()-1);
                invalid++;
            }
            finally 
            {
                puzzles.trimToSize();
            }    
       }
        Iterator iter = puzzles.listIterator();
        while(iter.hasNext())
        {
            iter.next();
            valid++;
        }
        System.out.println("There are " + total + " total files.");
        System.out.println("There are " + invalid + " invalid files.");
        System.out.println("One of " + valid + " valid puzzles will be generated.");
   }
   
   /**
    * Generates a random puzzle from the Stack of properly formatted puzzles.
    * @return a randomly generated puzzle from the Stack.
    */
   public static SudokuPuzzle getRandomPuzzle()
   {
       Random rand = new Random();
       return puzzles.get(rand.nextInt(valid));
   }
}

