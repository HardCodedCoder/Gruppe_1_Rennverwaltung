package at.fhburgenland.org.lecture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.LongSummaryStatistics;

public class Main {

    public static void main(String[] args) {
        /* TO DO
        Folgende Methoden sollen implementiert werden:
        - addPerson
        - readPerson
        - readAll
        - updatePerson
        - deletePerson
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sponsor");
        BaseEntityManager<Sponsor> sponsorEntityManager = new BaseEntityManager<>(
                factory.createEntityManager(),
                Sponsor.class);
        List<Sponsor> sponsors = sponsorEntityManager.readAll();
        for (Sponsor sponsor : sponsors) {
            System.out.println(sponsor);
        }

        Sponsor chonker = new Sponsor();
        chonker.setName("Mega Chonker");
        sponsorEntityManager.create(chonker);
        sponsors = sponsorEntityManager.readAll();
        for (Sponsor sponsor : sponsors) {
            System.out.println(sponsor);
        }

        chonker.setName("he is coming!");
        sponsorEntityManager.update(chonker);
        sponsors = sponsorEntityManager.readAll();
        for (Sponsor sponsor : sponsors) {
            System.out.println(sponsor);
        }
    }
}
