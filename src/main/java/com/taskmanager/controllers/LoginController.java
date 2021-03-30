package com.taskmanager.controllers;
import com.taskmanager.exceptions.InvalidRegistrationException;
import com.taskmanager.model.User;
import com.taskmanager.services.TaskService;
import com.taskmanager.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
      return "index";
   }

   @GetMapping("/register")
   public ModelAndView regForm() {
      ModelAndView mav = new ModelAndView("register");
      mav.addObject("user", new User());
      return mav;
   }

   @PostMapping("/register")
   public String register(@Valid User user, BindingResult result) throws InvalidRegistrationException {

      if (result.hasErrors()) {
         return "register";
      }

      String checkEmail = userService.getUserByUsername(user.getUsername()).toString();

      if (checkEmail.isEmpty()) {
         throw new InvalidRegistrationException();
      } else {
         userService.saveUser(user);
      }
      return "index";
   }
}
