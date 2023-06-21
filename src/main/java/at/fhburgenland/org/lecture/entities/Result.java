package at.fhburgenland.org.lecture.entities;


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
    @Column(name = "rennen_ID")
    private int resultId;

    @Id
    @OneToOne
    @JoinColumn(name = "fahrer_ID")
    @Column(name = "erster", nullable = false, insertable = false, updatable = false)
    private int firstId;

    @Id
    @OneToOne
    @JoinColumn(name = "fahrer_ID")
    @Column(name = "zweiter", nullable = false, insertable = false, updatable = false)
    private Driver secondId;

    @Id
    @OneToOne
    @JoinColumn(name = "fahrer_ID")
    @Column(name = "dritter", nullable = false, insertable = false, updatable = false)
    private Driver thirdId;


}
