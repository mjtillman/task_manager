package com.taskmanager.controllers;

import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TaskController {

   @Autowired
   private TaskService taskService;

   @GetMapping("/tasks/view_tasks")
   public String displayTasks(@ModelAttribute String logEmail,
                              Model model) {

      List<Task> tasks = taskService.getTasksByEmail(logEmail);
      model.addAttribute("tasks", tasks);
      return "/tasks/view_tasks";
   }

   @GetMapping("/tasks/add_tasks")
   public String addTasks(@ModelAttribute User user,
                          Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getEmail());
      model.addAttribute("tasks", tasks);
      return "/tasks/add_tasks";
   }

   @GetMapping("/tasks/update")
   public String updateTasks(@ModelAttribute User user, Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getEmail());
      model.addAttribute("tasks", tasks);
      return "/tasks/update_tasks";
   }

   @GetMapping("/tasks/remove")
   public String removeTasks(@ModelAttribute User user, Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getEmail());
      model.addAttribute("tasks", tasks);
      return "/tasks/remove_tasks";
   }
}
