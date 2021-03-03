package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RoleDaoImpl implements RoleDao{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(Role role) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(Role role) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Role.class, role.getId()));
        entityManager.getTransaction().commit();
    }

    @Override
    public Role getById(Long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.getReference(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from Role where name=?")
                .setParameter(1, name);
        return (Role)query.getSingleResult();
    }

    @Override
    public void update(Role role) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(role);
        entityManager.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listUsers() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
