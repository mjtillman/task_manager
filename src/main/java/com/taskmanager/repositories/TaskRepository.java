package com.taskmanager.repositories;

import com.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
   public Task findAllByName(String name);
   public List<Task> findAllByEmail(String email);
}
