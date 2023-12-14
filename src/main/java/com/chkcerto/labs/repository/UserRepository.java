package com.chkcerto.labs.repository;

import com.chkcerto.labs.entities.Role;
import com.chkcerto.labs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
