package com.find_user_github.repository;

import com.find_user_github.models.RepositoryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepo extends JpaRepository<RepositoryUser, Integer> {

}
