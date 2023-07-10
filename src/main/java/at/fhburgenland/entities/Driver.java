package at.fhburgenland.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Fahrer")
@Table(name = "fahrer")
@ToString
public class Driver implements Comparable<Driver> {
    /**
     * Holds the id of the driver
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fahrer_id", updatable = false, nullable = false)
    private int driverId;

    /**
     * Holds the first name of the driver
     */
    @Column(name = "vorname", nullable = false, length =50)
    private String firstName;

    /**
     * Holds the last name of the driver
     */
    @Column(name = "nachname", nullable = false, length =50)
    private String lastName;

    /**
     * Holds the nationality of the driver
     */
    @Column(name = "nationalitaet", nullable = false, length =50)
    private String nationality;

    /**
     * Holds the vehicle ID
     */
    @Column(name = "fahrzeug_id", nullable = false)
    private int vehicleId;

    /**
     * Holds the team ID
     */
    @Column(name = "team_id", nullable = false)
    private int teamId;

    @OneToOne
    @JoinColumn(name = "fahrzeug_id", insertable = false, updatable = false)
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "team_id", updatable = false, insertable = false)
    private Team team;

    /**
     * Initializes a new instance of the Driver class.
     */
    public Driver() {

    }

    /**
     * Initializes a new instance of the Driver class.
     * @param firstName The first name of the driver.
     * @param lastName The last name of the driver.
     * @param nationality The nationality of the driver.
     * @param vehicleId The vehicle ID.
     * @param teamId The Team ID.
     */
    public Driver(String firstName, String lastName, String nationality, int vehicleId, int teamId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.vehicleId = vehicleId;
        this.teamId = teamId;
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
    public int compareTo(Driver o) {
        return Integer.compare(this.driverId, o.driverId);
    }
}
