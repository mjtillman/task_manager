package com.taskmanager.controllers;

import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.exceptions.InvalidRegistrationException;
import com.taskmanager.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

   @ExceptionHandler(value = InvalidLoginException.class)
   public String loginError(Model model) {
      model.addAttribute("user", new User());
      model.addAttribute("loginErr", true);

      return "index";
   }

   @ExceptionHandler(value = InvalidRegistrationException.class)
   public String regError(Model model) {
      model.addAttribute("user", new User());
      model.addAttribute("regErr", true);

      return "register";
   }
}
