package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public boolean add(Role role) {
        Role existedRole = roleDao.getByName(role.getRole());

        if (existedRole != null){
            return false;
        }
        roleDao.add(role);
        return true;
    }

    @Transactional
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Transactional
    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }

    @Transactional
    @Override
    public boolean update(Role role) {
        Role existedRole = roleDao.getByName(role.getRole());

        if (existedRole != null){
            return false;
        }
        roleDao.update(role);
        return true;
    }

    @Transactional
    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }
}
