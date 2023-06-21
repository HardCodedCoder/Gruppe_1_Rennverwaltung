package at.fhburgenland.org.lecture.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Team")
@Table(name = "team")
@ToString
public class Team {
    /**
     * Holds the id of the team.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_ID", updatable = false, nullable = false, unique = true)
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
    @Column(name = "sponsor_ID", nullable = false, updatable = false, insertable = false)
    private int sponsorId;
}
