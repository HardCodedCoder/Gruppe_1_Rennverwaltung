package at.fhburgenland.org.lecture.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Rennen")
@Table(name = "rennen")
@ToString
public class Race {
    /**
     * Holds the id of the race
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rennen_ID", updatable = false, nullable = false, unique = true)
    private int raceId;

    /**
     * Holds the name of the race.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * Holds the date of the team.
     */
    @Column(name = "datum", nullable = false)
    private LocalDate date;

    /**
     * Holds the racetrack ID
     */
    @Column(name = "rennstrecken_ID", nullable = false, updatable = false, insertable = false)
    private int raceTrackId;
}
