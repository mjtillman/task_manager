package com.taskmanager.repositories;

import com.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
   public Task findAllByName(String name);
   public List<Task> findAllByEmail(String email);
   public List<Task> findAll();
   public Optional<Task> findById(Integer id);
}
