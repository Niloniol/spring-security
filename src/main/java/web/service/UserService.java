package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {
    boolean add(User user, List<Role> roles);
    void remove(User user);
    User getById(Long id);
    User getByName(String name);
    boolean update(User user, List<Role> roles);
    List<User> listUsers();
}
