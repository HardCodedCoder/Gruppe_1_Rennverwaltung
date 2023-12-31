package at.fhburgenland.database.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Ausfall")
@Table(name = "ausfall")
@ToString
public class Outage implements Comparable<Outage> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ausfall_id")
    private int outageId;

    @Column(name = "fahrer_id", nullable = false)
    private int driverId;

    @Column(name = "rennen_id", nullable = false)
    private int raceId;

    @Column(name = "ausfallszenario", nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "rennen_id",  insertable = false, updatable = false)
    private Race race;

    @OneToOne
    @JoinColumn(name = "fahrer_id",  insertable = false, updatable = false)
    private Driver driver;

    public Outage() {

    }

    public Outage(int driverId, int raceId) {
        this.driverId = driverId;
        this.raceId = raceId;

    }
    public Outage(int driverId, int raceId, String reason, Driver driver, Race race) {
        this.driverId = driverId;
        this.raceId = raceId;
        this.reason = reason;
        this.driver = driver;
        this.race = race;
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
    public int compareTo(Outage o) {
        return Integer.compare(this.outageId, o.outageId);
    }
}
