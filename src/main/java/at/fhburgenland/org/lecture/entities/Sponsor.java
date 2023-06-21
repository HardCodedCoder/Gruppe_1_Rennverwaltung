package at.fhburgenland.org.lecture.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Sponsor")
@Table(name = "sponsor")
@ToString
public class Sponsor {
    /**
     * Holds the id of the sponsor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_ID", updatable = false, nullable = false, unique = true)
    private int sponsorId;

    /**
     * Holds the name of the sponsor.
     */
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * Initializes a new instance of the Sponsor class.
     */
    public Sponsor()
    {
    }

    /**
     * Initializes a new instance of the Sponsor class.
     * @param sponsorId
     * @param name
     */
    public Sponsor(int sponsorId, String name)
    {
        this.sponsorId = sponsorId;
        this.name = name;
    }
}
