package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> index();

    User show(long id);

    void save(User user);

    void update(long id, User updateUser);

    void delete(long id);

}
