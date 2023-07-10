package at.fhburgenland.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Fahrzeug")
@Table(name = "fahrzeug")
@ToString
public class Vehicle implements Comparable<Vehicle> {
    /**
     * Holds the id of the vehicle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fahrzeug_id", updatable = false, nullable = false, unique = true)
    private int vehicleId;

    /**
     * Holds the brand of the vehicle
     */
    @Column(name = "marke", nullable = false, length = 50)
    private String brand;

    /**
     * Holds the model of the vehicle
     */
    @Column(name = "modell", nullable = false, length = 50)
    private String model;

    /**
     * holds the year of construction of the vehicle
     */
    @Column(name = "baujahr", nullable = false)
    private int constructionYear;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    public Vehicle() {

    }

    /**
     * Initializes a new instance of the Vehicle class.
     * @param vehicleBrand The brand of the vehicle.
     * @param vehicleModel The model.
     * @param constructionYear The year when the vehicle was constructed.
     */
    public Vehicle(String vehicleBrand, String vehicleModel, int constructionYear) {
        this.brand = vehicleBrand;
        this.model = vehicleModel;
        this.constructionYear = constructionYear;
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
    public int compareTo(Vehicle o) {
        return Integer.compare(this.vehicleId, o.vehicleId);
    }
}
