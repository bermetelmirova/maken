package kg.academy.maken.service.impl;

import kg.academy.maken.entity.UserRole;
import kg.academy.maken.repository.UserRoleRepository;
import kg.academy.maken.service.UserRoleService;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(Long id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public UserRole deleteById(Long id) {
        UserRole userRole = findById(id);
        if (userRole != null)
            userRoleRepository.deleteById(id);
        return userRole;
    }
}
