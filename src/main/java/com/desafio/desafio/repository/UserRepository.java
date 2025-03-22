package com.desafio.desafio.repository;

import com.desafio.desafio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
