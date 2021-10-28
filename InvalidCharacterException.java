package sudoku;
import java.util.InputMismatchException;
/**
 * Exception thrown when characters input by the user that are not integers 1-9.
 * @author Anthony Clavette
 * @version 4-23-2020
 */
public class InvalidCharacterException extends InputMismatchException
{
    public InvalidCharacterException(String message)
    {
        super(message);
    }
}

