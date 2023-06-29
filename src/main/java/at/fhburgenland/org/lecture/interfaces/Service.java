package at.fhburgenland.org.lecture.interfaces;

/**
 * Interface for the service which hosts the business logic.
 */
public interface Service {
    /**
     * Runs the service which hosts the business logic.
     */
    void runService();

    /**
     * Signals the service to exit.
     */
    void signalExit();

    /**
     * Gets the IOHandler.
     * @return
     */
    IOHandler getIOHandler();

    /**
     * Gets the entire menu of the service.
     * @return
     */
    Menu getMenu();

    /**
     * Checks wether the service should return to the main menu.
     */
    boolean isReturningToMain();

    /**
     * Signals the service to return to the main menu.
     */
    void signalReturningToMain();
}
