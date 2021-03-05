package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean add(User user, List<Role> roles) {
        if (userInit(user, roles)) return false;
        userDao.add(user);
        return true;
    }

    private boolean userInit(User user, List<Role> rolesCh) {

        List<Role> newRoles = new ArrayList<>();
        if(!rolesCh.isEmpty()){
            for (Role role : rolesCh) {
                newRoles.add(roleDao.getByName(role.getRole()));
            }
        }
        user.setRoles(newRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return false;
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Transactional
    @Override
    public boolean update(User user, List<Role> roles) {
        if (userInit(user, roles)) return false;
        userDao.update(user);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
