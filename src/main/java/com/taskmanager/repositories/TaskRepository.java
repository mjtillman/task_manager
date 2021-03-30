package com.taskmanager.repositories;

import com.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
   public Task findAllByName(String name);
   public List<Task> findAllByEmail(String email);
   public List<Task> findAll();
}
