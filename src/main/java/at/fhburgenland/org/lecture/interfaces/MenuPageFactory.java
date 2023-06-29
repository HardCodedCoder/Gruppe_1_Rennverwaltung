package at.fhburgenland.org.lecture.interfaces;

import at.fhburgenland.org.lecture.enumerations.MenuPages;

public interface MenuPageFactory {
    MenuPage createMenuPage(MenuPages menuPageToCreate);
}
