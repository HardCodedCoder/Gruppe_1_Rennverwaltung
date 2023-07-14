package at.fhburgenland.menu.menuObserver;

import at.fhburgenland.RaceManagementService;
import at.fhburgenland.database.entities.Driver;
import at.fhburgenland.database.entities.Race;
import at.fhburgenland.database.entities.Result;
import at.fhburgenland.interfaces.Service;
import at.fhburgenland.enumerations.MenuPages;
import at.fhburgenland.utility.QueryResult;
import ch.qos.logback.core.encoder.EchoEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
            this.executeReadForRace();
            var date = this.service.getIOHandler().getDateFromUser("Bitte geben Sie das Datum in Form von <dd.mm.yyyy> des Rennen ein, das Sie abfragen möchten: ");
            List<Result> results = RaceManagementService.getEntityManagerMap().get(Result.class).readAll();
            List<QueryResult> queryResults = new ArrayList<>();
            for (Result result : results) {
                if (result.getRace().getDate().compareTo(date) == 0) {
                    String raceName = result.getRace().getName();

                    QueryResult first = new QueryResult(
                            raceName,
                            date,
                            result.getFirstDriver().getFirstName(),
                            result.getFirstDriver().getLastName(),
                            result.getFirstDriver().getTeam().getName(),
                            result.getFirstDriver().getVehicle().getBrand(),
                            result.getFirstDriver().getVehicle().getModel()
                    );
                    queryResults.add(first);

                    QueryResult second = new QueryResult(
                            raceName,
                            date,
                            result.getSecondDriver().getFirstName(),
                            result.getSecondDriver().getLastName(),
                            result.getSecondDriver().getTeam().getName(),
                            result.getSecondDriver().getVehicle().getBrand(),
                            result.getSecondDriver().getVehicle().getModel()
                    );
                    queryResults.add(second);

                    if (result.getThirdDriver()!= null) {
                        QueryResult third = new QueryResult(
                                raceName,
                                date,
                                result.getThirdDriver().getFirstName(),
                                result.getThirdDriver().getLastName(),
                                result.getThirdDriver().getTeam().getName(),
                                result.getThirdDriver().getVehicle().getBrand(),
                                result.getThirdDriver().getVehicle().getModel()
                        );
                        queryResults.add(third);
                    }
                }
            }

            this.service.getIOHandler().renderQueryResults(queryResults);
        }
    }

