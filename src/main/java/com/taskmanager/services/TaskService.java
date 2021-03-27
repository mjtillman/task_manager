package com.taskmanager.services;

import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepo;

   public List<Task> getAllTasks() {
      return taskRepo.findAll();
   }

   public List<Task> getTasksByUser(User user) {
      return taskRepo.findAllByEmail(user.getEmail());
   }
}
