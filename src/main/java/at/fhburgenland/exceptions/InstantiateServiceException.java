package at.fhburgenland.exceptions;

/**
 * This class represents an exception which can be user to signalize that the instantiation of a Service
 * was unsuccessful due to an error during instantiation.
 */
public class InstantiateServiceException extends RuntimeException
{
    /**
     * Initializes a new instance of the InstantiateServiceException class.
     * @param message The message to pass.
     */
    public InstantiateServiceException(String message) {
        super(message);
    }
}
