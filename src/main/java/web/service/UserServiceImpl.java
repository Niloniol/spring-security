package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean add(User user) {
        User existedUser = userDao.getByName(user.getUsername());

        if (existedUser != null){
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return true;
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
    public boolean update(User user) {
        User existedUser = userDao.getByName(user.getUsername());

        if (existedUser != null){
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
