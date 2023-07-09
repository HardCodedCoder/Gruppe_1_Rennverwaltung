package at.fhburgenland;

import at.fhburgenland.entities.Result;
import at.fhburgenland.org.lecture.BaseEntityManager;

import javax.persistence.EntityManager;

public class ResultEntityManager extends BaseEntityManager<Result> {

    public ResultEntityManager(EntityManager entityManager) {
        super(entityManager, Result.class);
    }

    public Result readResult(Result result) {
        if (entityManager.isOpen()) {
            return this.entityManager.find(this.entityClass, result);
        } else {
            throw new IllegalStateException("The entity Manager is not open!");
        }
    }
}
