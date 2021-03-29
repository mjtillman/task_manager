package com.taskmanager.controllers;

import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.exceptions.InvalidRegistrationException;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.services.TaskService;
import com.taskmanager.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

   @Autowired
   UserService userService;

   @Autowired
   TaskService taskService;

   Logger log = LoggerFactory.getLogger(LoginController.class);

   @GetMapping({"/", "/login"})
   public String home(Model model) {
      model.addAttribute("user", new User());
      model.addAttribute("content", "login");
      return "index";
   }

   @PostMapping({"/", "/login"})
   public String submitLogin(@ModelAttribute User user,
                             Model model) throws InvalidLoginException {
      User checkUser;
      checkUser = userService.getUserByEmail(user.getEmail());

      String password = user.getPassword();

      if (password.equals(checkUser.getPassword())) {
         List<Task> tasks = taskService.getTasksByEmail(user.getEmail());
         model.addAttribute("logEmail", user.getEmail());
         model.addAttribute("tasks", tasks);
      } else {
         throw new InvalidLoginException(user.getEmail());
      }

      return "/tasks/view_tasks";
   }

   @GetMapping("/register")
   public String regForm(Model model) {
      model.addAttribute("newUser", new User());
      return "register";
   }

   @PostMapping("/register")
   public String register( @ModelAttribute User newUser,
                           Model model) throws InvalidRegistrationException {

      log.debug("New User: {}", newUser.getEmail());

      String checkEmail = userService.getUserByEmail(newUser.getEmail()).toString();

      log.debug("Check User: {}", checkEmail);

      if (checkEmail.isEmpty()) {
         throw new InvalidRegistrationException();
      } else {
         userService.updateUser(newUser);
         model.addAttribute("regSuccess", true);
      }
      return "index";
   }
}
