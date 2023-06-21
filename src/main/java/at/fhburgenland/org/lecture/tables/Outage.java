package at.fhburgenland.org.lecture.tables;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Ausfall")
@Table(name = "ausfall")
@ToString
public class Outage {
}
