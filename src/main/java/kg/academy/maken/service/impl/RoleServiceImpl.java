package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Role;
import kg.academy.maken.repository.RoleRepository;
import kg.academy.maken.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role deleteById(Long id) {
        Role role = findById(id);
        if (role != null)
            roleRepository.deleteById(id);
        return role;
    }
}
