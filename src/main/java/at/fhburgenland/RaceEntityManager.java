package at.fhburgenland;

import at.fhburgenland.entities.Race;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RaceEntityManager extends BaseEntityManager<Race>{
    public RaceEntityManager(EntityManager entityManager) {
        super(entityManager, Race.class);
    }

    public boolean doesResultExistForRace(int rennenId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Race> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(criteriaBuilder.equal(root.get("raceId"), rennenId));
        Long count = entityManager.createQuery(criteriaQuery).getSingleResult();
        return count > 0;
    }
}
