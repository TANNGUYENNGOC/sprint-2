package com.car_management.repository.user;

import com.car_management.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Integer> {
        Optional<User> findByUserName(String userName);

        Boolean existsByUserName(String userName);

        Boolean existsByEmail(String email);

        User findByEmail(String email);

}