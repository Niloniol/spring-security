package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
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
        Query query = entityManager.createQuery("from Role where role=?0")
                .setParameter(0, name);
        try {
            return (Role)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
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
    public List<Role> listRoles() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("from Role");
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
