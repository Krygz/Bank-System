package com.system.bank.repository;

import com.system.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User>findByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
