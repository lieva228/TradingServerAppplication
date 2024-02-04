package com.example.backend.repository;

import com.example.backend.model.Assets;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
    Assets findByUser(User user);
}
