package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Role;
import kg.academy.maken.repository.RoleRepository;
import kg.academy.maken.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

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
