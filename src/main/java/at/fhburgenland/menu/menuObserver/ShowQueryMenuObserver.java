package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.Service;

public class ShowQueryMenuObserver extends BaseMenuObserver {
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public ShowQueryMenuObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        this.handleSubMenu(MenuPages.QUERY);
    }
}
