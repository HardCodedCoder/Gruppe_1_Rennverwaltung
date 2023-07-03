package at.fhburgenland.interfaces;

import at.fhburgenland.enumerations.MenuPages;

public interface MenuObserver
{
    void update(MenuPages fromMenu);
}
