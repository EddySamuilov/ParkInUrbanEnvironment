package com.example.carpark.repository;

import com.example.carpark.entity.RoleEntity;
import com.example.carpark.entity.RoleEnumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByRole(RoleEnumType roleName);
}
