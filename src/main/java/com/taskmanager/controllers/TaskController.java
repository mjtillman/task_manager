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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
   public ModelAndView addTasks() {
      ModelAndView mav = new ModelAndView("/tasks/add_tasks");
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      mav.addObject("user", user);
      mav.addObject("newTask", new Task());
      return mav;
   }

   @PostMapping("/tasks/add_tasks")
   public ModelAndView addTasks(@ModelAttribute Task newTask) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      newTask.setUser(user);
      newTask.setEmail(user.getUsername());
      taskService.saveTask(newTask);

      ModelAndView mav = new ModelAndView("/tasks/view_tasks");
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      mav.addObject("tasks", tasks);
      mav.addObject("user", user);
      return mav;
   }

   @GetMapping("/tasks/update_tasks")
   public ModelAndView updateTasks() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      ModelAndView mav = new ModelAndView("/tasks/update_tasks");
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      mav.addObject("user", user);
      mav.addObject("updateId", "");
      mav.addObject("tasks", tasks);
      return mav;
   }

   @PostMapping("/tasks/update_task")
   public ModelAndView updateForm(@ModelAttribute String updateId, Model model) throws Exception {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      int id = Integer.parseInt(updateId);
      User user = userService.getUserByUsername(auth.getName());
      Optional<Task> updateTask = taskService.getTaskById(id);

      ModelAndView mav = new ModelAndView();

      if (updateTask.isPresent()) {
         mav.setViewName("/tasks/update_form");
         mav.addObject("user", user);
         mav.addObject("updateTask", updateTask);
      } else {
         mav.setViewName("/tasks/update_tasks");
      }

      return mav;
   }

   @GetMapping("/tasks/update_form")
   public ModelAndView sendUpdateForm(Model model) {
      ModelAndView mav = new ModelAndView("/tasks/update_form");
      mav.addObject("user", model.getAttribute("user"));
      mav.addObject("updateTask", model.getAttribute("updateTask"));
      return mav;
   }

   @GetMapping("/tasks/remove_tasks")
   public ModelAndView removeTasks() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      ModelAndView mav = new ModelAndView("/tasks/remove_tasks");
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      mav.addObject("user", user);
      mav.addObject("tasks", tasks);
      return mav;
   }
}
