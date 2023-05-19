package com.car_management.service.user;

import com.car_management.model.user.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName (String name);
}
