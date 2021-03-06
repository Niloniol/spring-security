package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void remove(User user);
    User getById(Long id);
    User getByName(String name);
    void update(User user);
    List<User> listUsers();
}
