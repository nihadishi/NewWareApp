package com.example.warehouseapp.dao.repository;

import com.example.warehouseapp.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserByEmail(String email);
}
