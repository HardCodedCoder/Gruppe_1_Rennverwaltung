package at.fhburgenland.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Ergebnis")
@Table(name = "ergebnis")
@ToString
public class Result {
    @Id
    @Column(name = "rennen_id")
    private int raceId;

    @Id
    @JoinColumn(name = "fahrer_id")
    @Column(name = "erster", nullable = false, updatable = false)
    private int firstId;

    @Id
    @JoinColumn(name = "fahrer_id")
    @Column(name = "zweiter", nullable = false, updatable = false)
    private int secondId;

    @JoinColumn(name = "fahrer_id")
    @Column(name = "dritter", nullable = true, updatable = false)
    private Integer thirdId;

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
    public Result(int raceId, int firstId, int secondId)
    {
        this.raceId = raceId;
        this.firstId = firstId;
        this.secondId = secondId;
    }

    /**
     * Initializes a new instance of the Result class.
     * @param raceId the id of the
     * @param firstId
     * @param secondId
     * @param thirdId
     */
    public Result(int raceId, int firstId, int secondId, int thirdId)
    {
        this.raceId = raceId;
        this.firstId = firstId;
        this.secondId = secondId;
        this.thirdId = thirdId;
    }

}
