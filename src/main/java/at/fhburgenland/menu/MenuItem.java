package at.fhburgenland.menu;

import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.interfaces.MenuObserver;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a selectable menu item.
 */
@Getter
@Setter
public class MenuItem
{
    /**
     * The text which should be displayed at the menu page.
     */
    private String label;

    /**
     * The number relevant for the ordering on the page.
     */
    private int number;

    /**
     * The listeners which "observe" the menu item.
     */
    private List<MenuObserver> observers = new ArrayList<>();

    private MenuPages fromMenuPage;

    /**
     * Initializes a new instance of the MenuItem class.
     * @param label
     * @param number
     */
    public MenuItem(String label, int number, MenuObserver listener, MenuPages fromMenuPage)
    {
        this.label = label;
        this.number = number;
        this.addMenuObserver(listener);
        this.fromMenuPage = fromMenuPage;
    }

    /**
     * Adds a listener to the list of listeners.
     * @param observer The listener to add.
     */
    public void addMenuObserver(MenuObserver observer)
    {
        this.observers.add(observer);
    }

    public void notifyObservers()
    {
        for (MenuObserver listener : this.observers)
        {
            listener.update(this.fromMenuPage);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.getNumber()) + " - " + this.getLabel();
    }
}
