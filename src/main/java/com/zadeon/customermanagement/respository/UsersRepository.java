package com.zadeon.customermanagement.respository;

import com.zadeon.customermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String Password);
}
