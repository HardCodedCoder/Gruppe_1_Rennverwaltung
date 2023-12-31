package at.fhburgenland.database.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Team")
@Table(name = "team")
@ToString
public class Team implements Comparable<Team> {
    /**
     * Holds the id of the team.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", updatable = false, nullable = false, unique = true)
    private int teamId;

    /**
     * Holds the name of the team.
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * Holds the founding year of the team.
     */
    @Column(name = "gruendungsjahr", nullable = false)
    private int foundingYear;

    /**
     * Holds the sponsor ID
     */
    @Column(name = "sponsor_id", nullable = false, updatable = false)
    private int sponsorId;

    @OneToOne
    @JoinColumn(name = "sponsor_id", insertable = false, updatable = false)
    private Sponsor sponsor;

    public Team(){

    }

    /**
     * Initializes the instance of the Team
     * @param name the name of the team
     * @param foundingYear the founding year of the team
     * @param sponsorId the sponsor ID
     */
    public Team(String name, int foundingYear, int sponsorId) {
        this.name = name;
        this.foundingYear = foundingYear;
        this.sponsorId = sponsorId;
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
    public int compareTo(Team o) {
        return Integer.compare(this.teamId, o.teamId);
    }
}
