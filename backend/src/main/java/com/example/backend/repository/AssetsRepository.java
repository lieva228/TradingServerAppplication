package com.example.backend.repository;

import com.example.backend.model.Assets;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    Optional<Assets> findByUser(User user);
}
