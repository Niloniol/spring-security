package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public boolean add(Role role) {
        Role existedRole = roleRepository.findByRole(role.getRole());

        if (existedRole != null){
            return false;
        }
        roleRepository.save(role);
        return true;
    }

    @Transactional
    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Role getByName(String name) {
        return roleRepository.findByRole(name);
    }

    @Transactional
    @Override
    public boolean update(Role role) {
        Role existedRole = roleRepository.findByRole(role.getRole());

        if (existedRole != null){
            return false;
        }
        roleRepository.save(role);
        return true;
    }

    @Transactional
    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}
