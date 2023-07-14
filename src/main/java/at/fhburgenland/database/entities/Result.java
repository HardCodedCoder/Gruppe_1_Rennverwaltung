package at.fhburgenland.database.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Ergebnis")
@Table(name = "ergebnis")
@ToString
public class Result implements Comparable<Result> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ergebnis_id", nullable = false, updatable = false)
    private int resultId;

    @Column(name = "rennen_id")
    private int raceId;

    @Column(name = "erster", nullable = false)
    private int firstId;

    @Column(name = "zweiter", nullable = false)
    private int secondId;

    @Column(name = "dritter")
    private Integer thirdId;

    @OneToOne
    @JoinColumn(name = "erster", referencedColumnName = "fahrer_id", insertable = false, updatable = false)
    private Driver firstDriver;

    @OneToOne
    @JoinColumn(name = "zweiter", referencedColumnName = "fahrer_id", insertable = false, updatable = false)
    private Driver secondDriver;

    @OneToOne
    @JoinColumn(name = "dritter", referencedColumnName = "fahrer_id", insertable = false, updatable = false)
    private Driver thirdDriver;

    @OneToOne
    @JoinColumn(name = "rennen_id", insertable = false, updatable = false)
    private Race race;

    /**
     * Initializes a new instance of the Result class.
     */
    public Result() {

    }

    /**
     *
     * @param raceId
     * @param firstId
     * @param secondId
     */
    public Result(int raceId, int firstId, int secondId, Driver firstDriver, Driver secondDriver, Race race)
    {
        this.raceId = raceId;
        this.firstId = firstId;
        this.secondId = secondId;
        this.firstDriver = firstDriver;
        this.secondDriver = secondDriver;
        this.race = race;
    }

    /**
     * Initializes a new instance of the Result class.
     * @param raceId the id of the
     * @param firstId
     * @param secondId
     * @param thirdId
     */
    public Result(int raceId, int firstId, int secondId, int thirdId,  Driver firstDriver, Driver secondDriver, Driver thirdDriver, Race race)
    {
        this.raceId = raceId;
        this.firstId = firstId;
        this.secondId = secondId;
        this.thirdId = thirdId;
        this.firstDriver = firstDriver;
        this.secondDriver = secondDriver;
        this.thirdDriver = thirdDriver;
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
    public int compareTo(Result o) {
        return Integer.compare(this.resultId, o.resultId);
    }
}
