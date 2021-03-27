package com.taskmanager.controllers;

import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvalidLoginController {

   @ExceptionHandler(value = InvalidLoginException.class)
   public ModelAndView loginError() {

      String errMsg = "Invalid credentials, please try again.";

      ModelAndView mav = new ModelAndView("index");
      mav.addObject("contents", "login");
      mav.addObject("user", new User());
      mav.addObject("errorMsg", errMsg);

      return mav;
   }
}
