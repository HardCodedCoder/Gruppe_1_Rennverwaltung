package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.Service;

public class CreateObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public CreateObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {

    }
}
