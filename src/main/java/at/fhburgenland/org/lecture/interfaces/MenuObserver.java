package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.enumerations.MenuPages;

public interface MenuObserver
{
    void update(MenuPages fromMenu);
}
