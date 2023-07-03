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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rennen_id")
    private int resultId;

    @Id
    @JoinColumn(name = "fahrer_id")
    @Column(name = "erster", nullable = false, insertable = false, updatable = false)
    private int firstId;

    @Id
    @JoinColumn(name = "fahrer_id")
    @Column(name = "zweiter", nullable = false, insertable = false, updatable = false)
    private int secondId;

    @Id
    @JoinColumn(name = "fahrer_id")
    @Column(name = "dritter", nullable = false, insertable = false, updatable = false)
    private int thirdId;


}
