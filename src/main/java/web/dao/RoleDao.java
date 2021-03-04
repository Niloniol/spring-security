package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {
    void add(Role role);
    void remove(Role role);
    Role getById(Long id);
    Role getByName(String name);
    void update(Role role);
    List<Role> listRoles();
}
