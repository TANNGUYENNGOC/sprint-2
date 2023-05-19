package com.car_management.repository.user;

import com.car_management.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select * from roles where name = :name", nativeQuery = true)
    Optional<Role> findWithName (@Param("name") String name);
}
