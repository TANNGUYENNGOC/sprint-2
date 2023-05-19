package com.car_management.service.user.impl;

import com.car_management.model.user.Role;
import com.car_management.repository.user.IRoleRepository;
import com.car_management.service.user.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findWithName(name);
    }
}
