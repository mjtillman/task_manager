package com.taskmanager.controllers;

import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.services.TaskService;
import com.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

   @Autowired
   private UserService userService;

   @Autowired
   private TaskService taskService;

   @GetMapping("/tasks/view_tasks")
   public ModelAndView displayTasks(Principal principal) {
      ModelAndView mav = new ModelAndView("/tasks/view_tasks");
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());

      mav.addObject("username", user.getUsername());
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
     mav.addObject("tasks", tasks);
      return mav;
   }

   @GetMapping("/tasks/add_tasks")
   public String addTasks(@ModelAttribute User user,
                          Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      model.addAttribute("tasks", tasks);
      return "add_tasks";
   }

   @GetMapping("/tasks/update")
   public String updateTasks(@ModelAttribute User user, Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      model.addAttribute("tasks", tasks);
      return "update_tasks";
   }

   @GetMapping("/tasks/remove")
   public String removeTasks(@ModelAttribute User user, Model model) {
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      model.addAttribute("tasks", tasks);
      return "remove_tasks";
   }
}
