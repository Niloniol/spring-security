package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private boolean userInit(User user, List<Role> rolesCh) {

        List<Role> newRoles = new ArrayList<>();
        if(!rolesCh.isEmpty()){
            for (Role role : rolesCh) {
                newRoles.add(roleRepository.findByRole(role.getRole()));
            }
        }
        user.setRoles(newRoles);
        return false;
    }

    @Transactional
    @Override
    public boolean add(User user, List<Role> roles) {
        if (userInit(user, roles)) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public boolean remove(User user) {
        User findUser = userRepository.findByUsername(user.getUsername());
        if (findUser != null) {
            userRepository.deleteById(findUser.getId());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeByUsername(String username) {
        User findUser = userRepository.findByUsername(username);
        if (findUser != null) {
            userRepository.deleteById(findUser.getId());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User getByName(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Transactional
    @Override
    public boolean update(User user, List<Role> roles) {
        if (userInit(user, roles)) return false;
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
