package at.fhburgenland.org.lecture.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "Rennstrecke")
@Table(name = "rennstrecke")
@ToString
public class RaceTrack {
    /**
     * Holds the id of the racetrack
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rennstrecken_ID", updatable = false, nullable = false, unique = true)
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
}
