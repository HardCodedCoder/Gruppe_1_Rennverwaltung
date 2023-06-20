/*package at.fhburgenland.org.lecture;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PersonEntityManager
{
    private EntityManagerFactory entityManagerFactory;
    private List<Person> personList;
    public PersonEntityManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("person");
        this.personList = new ArrayList<>();
    }

    public void close() {
        if (this.entityManagerFactory != null && this.entityManagerFactory.isOpen())
            this.entityManagerFactory.close();
    }

    public void readAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        String query = "SELECT p FROM Person p"; // * geht hier nicht, daher p
        TypedQuery<Person> typedQuery = entityManager.createQuery(query, Person.class);
        List<Person> personList = null;

        try{
            personList = typedQuery.getResultList();
            personList.forEach(person -> System.out.println(
                    "PNr: " + person.getPersonId() +
                            "Vorname: " + person.getForename() +
                            "Nachname: " + person.getSurname() +
                            "Gehalt: " + person.getSalary()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        } finally { entityManager.close();

        }
    }

    public void add(String forename, String lastname, int salary) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Person person = new Person();
        person.setForename(forename);
        person.setSurname(lastname);
        person.setSalary(salary);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }

    public void update(int personId, String forename, String lastname, int salary) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Person person = entityManager.find(Person.class, personId);
            person.setForename(forename);
            person.setSurname(lastname);
            person.setSalary(salary);
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void delete(int personId) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Person person = entityManager.find(Person.class, personId);
            entityManager.remove(person);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void readPerson(int personId) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            Person person = entityManager.find(Person.class, personId);
            this.printPerson(person);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private void printPerson(Person person) {
        System.out.println(
                "PNr: " + person.getPersonId() +
                        "Vorname: " + person.getForename() +
                        "Nachname: " + person.getSurname() +
                        "Gehalt: " + person.getSalary()
        );
    }
}
*/
