package com.find_user_github.repository;

import com.find_user_github.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
