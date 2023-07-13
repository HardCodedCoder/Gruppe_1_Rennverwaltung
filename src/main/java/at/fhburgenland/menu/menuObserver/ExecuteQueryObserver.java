package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.database.entities.Race;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;
import ch.qos.logback.core.encoder.EchoEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExecuteQueryObserver extends BaseMenuObserver{
    /**
     * Initializes a new instance of the BaseMenuObserver class.
     *
     * @param service The service.
     */
    public ExecuteQueryObserver(Service service) {
        super(service);
    }

    @Override
    public void update(MenuPages fromMenu) {
        String dateString = "2021-07-04";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date desiredDate = null;
        try {
            desiredDate = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("basodlnahs ldfnjasöplojdasd");
        }

        List<Object[]> results = RaceManagementService.getEntityManagerMap().get(Race.class).readByDate(desiredDate);
        for (Object[] result : results) {
            String rennenName = (String) result[0];
            Date rennenDatum = (Date) result[1];
            String fahrerVorname = (String) result[2];
            String fahrerNachname = (String) result[3];
            String teamName = (String) result[4];
            String fahrzeugMarke = (String) result[5];
            String fahrzeugModell = (String) result[6];

        }
    }
}
