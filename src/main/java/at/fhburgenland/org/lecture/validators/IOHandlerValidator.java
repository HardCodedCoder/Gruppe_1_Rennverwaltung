package at.fhburgenland.org.lecture.validators;

import at.fhburgenland.org.lecture.interfaces.IOHandler;

/**
 * This class validates if an IOHandler is a valid instance.
 */
public class IOHandlerValidator
{
    /**
     * Validates if the ioHandler is a valid instance.
     * @param ioHandler The ioHandler to validate.
     * @return True if the passed ioHandler is a valid instance, otherwise false.
     */
    public boolean validate(IOHandler ioHandler)
    {
        return ioHandler == null ? false : true;
    }
}
