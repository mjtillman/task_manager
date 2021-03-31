package com.taskmanager.controllers;

import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.services.TaskService;
import com.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;
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
      mav.addObject("tasks", tasks);
      return mav;
   }

   @PostMapping("/tasks/update_task")
   public ModelAndView updateForm(@RequestParam (value="updateTask") String updateTask) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Integer id = Integer.parseInt(updateTask);
      User user = userService.getUserByUsername(auth.getName());
      Optional<Task> update = taskService.getTaskById(id);

      ModelAndView mav = new ModelAndView();

      if (update.isPresent()) {
         Task updateFormTask = update.get();
         mav.setViewName("/tasks/update_form");
         mav.addObject("user", user);
         mav.addObject("updateTask", updateFormTask);
      } else {
         mav.setViewName("/tasks/update_tasks");
      }

      return mav;
   }

   @GetMapping("/tasks/update_form")
   public ModelAndView sendUpdateForm(@ModelAttribute Task updateTask) {
      ModelAndView mav = new ModelAndView("/tasks/update_form");
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      mav.addObject("updateTask", updateTask);
      mav.addObject("user", user);
      return mav;
   }

   @PostMapping("/tasks/update_form")
   public ModelAndView postUpdateForm(@RequestParam (value="taskName") String taskName,
                                      @RequestParam (value="severity") String sev,
                                      @RequestParam (value="startDate")
                                       @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                      @RequestParam (value="endDate")
                                         @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                                      @RequestParam (value="description") String desc,
                                      @RequestParam (value="taskId") String id
                                      ) throws Exception {

      Optional<Task> option = taskService.getTaskById(Integer.parseInt(id));
      Task updateTask = new Task();

      if (option.isPresent()) {
         updateTask = option.get();
         updateTask.setName(taskName);
         updateTask.setSeverity(sev);
         updateTask.setStartDate(startDate);
         updateTask.setEndDate(endDate);
         updateTask.setDescription(desc);
         taskService.updateTask(updateTask);
      } else {
         throw new Exception("update form didn't work");
      }

      ModelAndView mav = new ModelAndView("/tasks/view_tasks");
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());
      mav.addObject("tasks", taskService.getTasksByEmail(user.getUsername()));
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

   @PostMapping("/tasks/remove_tasks")
   public ModelAndView removeTask(@RequestParam(value = "removeTask") String removeId) throws Exception {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.getUserByUsername(auth.getName());

      Optional<Task> optTask = taskService.getTaskById(Integer.parseInt(removeId));
      if (optTask.isPresent()) {
         Task removeTask = optTask.get();
         taskService.deleteTask(removeTask);
      } else {
         throw new Exception("remove did not work");
      }

      ModelAndView mav = new ModelAndView("/tasks/view_tasks");
      List<Task> tasks = taskService.getTasksByEmail(user.getUsername());
      mav.addObject("user", user);
      mav.addObject("tasks", tasks);
      return mav;
   }
}
