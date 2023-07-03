package at.fhburgenland.interfaces;

import at.fhburgenland.enumerations.MenuPages;

public interface MenuPageFactory {
    MenuPage createMenuPage(MenuPages menuPageToCreate);
}
