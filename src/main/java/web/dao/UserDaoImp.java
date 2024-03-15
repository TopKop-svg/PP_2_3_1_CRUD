package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    public User show(long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
    @Override
    public void update(long id, User updateUser) {
        User user = entityManager.find(User.class, id);
        user.setName(updateUser.getName());
        user.setLastName(updateUser.getLastName());
        user.setEmail(updateUser.getEmail());
        entityManager.persist(user);
    }
    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

}
