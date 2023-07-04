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
public class Vehicle {
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
}