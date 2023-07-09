package at.fhburgenland;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            throw new IllegalStateException("The entity manager is not open!");

        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction() != null)
                entityManager.getTransaction().rollback();
            System.err.println("An error occurred while updating entity: " + entity.toString() + ".");
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

    public void close() {
        this.entityManager.close();
    }
}
