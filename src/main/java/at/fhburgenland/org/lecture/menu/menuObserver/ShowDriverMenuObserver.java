package at.fhburgenland.org.lecture.menu.menuObserver;

import at.fhburgenland.org.lecture.enumerations.MenuPages;
import at.fhburgenland.org.lecture.interfaces.IOHandler;
import at.fhburgenland.org.lecture.interfaces.Menu;
import at.fhburgenland.org.lecture.interfaces.Service;
import at.fhburgenland.org.lecture.menu.MenuItem;

public class ShowDriverMenuObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public ShowDriverMenuObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        this.handleSubMenu(MenuPages.DRIVER);
    }
}
