package at.fhburgenland.utility;

import java.time.LocalDate;

public record QueryResult(String raceName,
                          LocalDate raceDate,
                          String driverFirstName,
                          String driverLastName,
                          String teamName,
                          String vehicleBrand,
                          String vehicleModel)
{
}
