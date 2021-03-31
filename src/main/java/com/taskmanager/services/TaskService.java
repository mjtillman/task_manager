package com.taskmanager.services;

import com.taskmanager.model.Task;
import com.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepo;

   public List<Task> getAllTasks() {
      return taskRepo.findAll();
   }

   public List<Task> getTasksByEmail(String email) {
      return taskRepo.findAllByEmail(email);
   }

   public Optional<Task> getTaskById(int id) { return taskRepo.findById(id); }

   public void saveTask(Task task) {
      taskRepo.save(task);
   }

   public void updateTask(Task task) { taskRepo.save(task); }

   public void deleteTask(Task task) { taskRepo.delete(task); }
}
