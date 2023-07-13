package at.fhburgenland.database.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Rennen")
@Table(name = "rennen")
@ToString
public class Race implements Comparable<Race>{
    /**
     * Holds the id of the race
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rennen_id", updatable = false, nullable = false, unique = true)
    private int raceId;

    /**
     * Holds the name of the race.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Holds the date of the team.
     */
    @Column(name = "datum", nullable = false)
    private LocalDate date;

    /**
     * Holds the racetrack ID
     */
    @Column(name = "rennstrecken_id", nullable = false, insertable = false, updatable = false)
    private Integer raceTrackId;

    public Race(){

    }

    /**
     * Initializes the instance of the Race
     * @param name the name of the Race
     * @param date the date of the Race
     * @param raceTrackId the racetrack id
     */
    public Race(String name, LocalDate date, int raceTrackId) {
        this.name = name;
        this.date = date;
        this.raceTrackId = raceTrackId;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Race o) {
        return Integer.compare(this.raceId, o.raceId);
    }
}