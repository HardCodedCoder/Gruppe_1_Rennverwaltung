package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.Service;

public class TerminateApplicationObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public TerminateApplicationObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        this.service.getIOHandler().println("Shutting down application!");
        if (this.service.getIOHandler().askToContinue("Are you sure you want to exit?"))
            this.service.signalExit();
    }
}
