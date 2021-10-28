package sudoku;
/**
 * Exception thrown when file input does not match the necessary formatting.
 * @author Anthony Clavette
 * @version 4-23-2020
 */
public class InvalidFileFormatException extends RuntimeException 
{
        public InvalidFileFormatException(String message)
        {
            super(message);
        }    
}
