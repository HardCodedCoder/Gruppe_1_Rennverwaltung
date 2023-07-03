package at.fhburgenland;

import at.fhburgenland.interfaces.IOHandler;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.exceptions.InstantiateServiceException;
import at.fhburgenland.view.ConsoleIOHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Starting application...");
        log.info("Starting Race Management Service...");
        IOHandler ioHandler = new ConsoleIOHandler();
        Service service = null;
        try {
            service = new RaceManagementService(ioHandler);
        } catch (InstantiateServiceException exception) {
            log.error(exception.getMessage() + "\nExiting...");
            System.err.println(exception.getMessage() + "\nExiting...");
            System.exit(1);
        }
        log.info("Initialization of the service completed.");
        log.info("Executing service...");
        service.runService();
        log.info("Shutting down application...");
    }
}
