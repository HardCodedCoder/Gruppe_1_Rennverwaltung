package at.fhburgenland.database.entitymanagers;

import at.fhburgenland.database.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class BaseEntityManager<T> {
    protected EntityManager entityManager;

    protected final Class<T> entityClass;

    public BaseEntityManager(EntityManager entityManager, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    public boolean create(T entity) {
        if (!this.entityManager.isOpen())
            throw new IllegalStateException("The entity manager is not open!");
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            System.err.println("An error occurred while creating entity: " + entity.toString() + ".");
            return false;
        }
    }

    public T read(int id) throws IllegalStateException {
        if (this.entityManager.isOpen())
            return this.entityManager.find(this.entityClass, id);
        else
            throw new IllegalStateException("The entity manager is not open!");
    }

    public T read(T instance) throws IllegalStateException {
        if (this.entityManager.isOpen())
            return this.entityManager.find(this.entityClass, instance);
        else
            throw new IllegalStateException("The entity manager is not open!");
    }

    public List<T> readAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
        Root<T> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(root);

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    public boolean update(T entity) {
        if (!this.entityManager.isOpen())
            throw new IllegalStateException("Der Entität-Manager ist nicht verfügbar!");

        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            System.err.println("\nEin Fehler wurde beim bearbeiten der Entität: " + entity.toString() + " ausgelöst.");
            return false;
        }
    }

    public boolean delete(T entity) throws IllegalStateException {
        if (!this.entityManager.isOpen())
            throw new IllegalStateException("The entity manager is not open!");
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(entity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction()!= null)
                entityManager.getTransaction().rollback();
            System.err.println("An error occurred while deleting entity: " + entity.toString() + ".");
            return false;
        }

        return true;
    }

    public List<Object[]> readByDate(Date date) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Race> raceRoot = criteriaQuery.from(Race.class);
        Join<Race, Result> resultJoin = raceRoot.join("ergebnis");
        Join<Result, Driver> driverJoin = resultJoin.join("fahrer");
        Join<Driver, Team> teamJoin= driverJoin.join("team");
        Join<Driver, Vehicle> vehicleJoin = driverJoin.join("fahrzeug");

        Expression<Object> caseExpression = criteriaBuilder.selectCase()
                .when(criteriaBuilder.equal(resultJoin.get("erster"), driverJoin.get("fahrerId")), 1)
                .when(criteriaBuilder.equal(resultJoin.get("zweiter"), driverJoin.get("fahrerId")), 2)
                .when(criteriaBuilder.equal(resultJoin.get("dritter"), driverJoin.get("fahrerId")), 3)
                        .otherwise(0);

        criteriaQuery.select(criteriaBuilder.array(
                raceRoot.get("name"),
                raceRoot.get("datum"),
                driverJoin.get("vorname"),
                driverJoin.get("nachname"),
                teamJoin.get("name"),
                vehicleJoin.get("marke"),
                vehicleJoin.get("modell")
        ))
                .where(criteriaBuilder.equal(raceRoot.get("datum"), date))
                .orderBy(criteriaBuilder.asc(caseExpression));

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void close() {
        this.entityManager.close();
    }
}
