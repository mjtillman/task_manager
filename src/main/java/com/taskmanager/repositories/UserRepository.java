package com.taskmanager.repositories;

import com.taskmanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   User findByUsername(String email);
   List<User> findAll();
}
