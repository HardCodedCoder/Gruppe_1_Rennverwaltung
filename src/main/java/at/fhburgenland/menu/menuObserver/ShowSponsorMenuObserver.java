package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;

public class ShowSponsorMenuObserver extends BaseMenuObserver{
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public ShowSponsorMenuObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        this.handleSubMenu(MenuPages.SPONSOR);
    }
}
