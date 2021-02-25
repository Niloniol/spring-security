package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, user.getId()));
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(Long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.getReference(User.class, id);
    }

    @Override
    public User getByName(String name) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from User where name=?")
                .setParameter(1, name);
        return (User)query.getSingleResult();
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
