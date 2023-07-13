package at.fhburgenland.database.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Rennstrecke")
@Table(name = "rennstrecke")
@ToString
public class RaceTrack implements Comparable<RaceTrack> {
    /**
     * Holds the id of the racetrack
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rennstrecken_id", updatable = false, nullable = false, unique = true)
    private int raceTrackId;

    /**
     * Holds the name of the racetrack
     */
    @Column(name = "Name", nullable = false, length = 20)
    private String name;

    /**
     * Holds the state of the racetrack
     */
    @Column(name = "bundesland", nullable = false, length = 20)
    private String state;

    /**
     * Holds the city of the racetrack
     */
    @Column(name = "stadt", nullable = false, length = 15)
    private String city;

    public RaceTrack(){
    }

    /**
     * Initializes the instance of the RaceTrack
     * @param name the name of the Race Track
     * @param state the state of the Race Track
     * @param city the city of the Race Track
     */
    public RaceTrack(String name, String state, String city) {
        this.name = name;
        this.state = state;
        this.city = city;
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
    public int compareTo(RaceTrack o) {
        return Integer.compare(this.raceTrackId, o.raceTrackId);
    }
}
