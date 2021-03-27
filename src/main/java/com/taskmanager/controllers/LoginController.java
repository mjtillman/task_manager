package com.taskmanager.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.model.User;
import com.taskmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

   @Autowired
   UserService userService;

   @GetMapping({"/", "/login"})
   public ModelAndView home() {
      ModelAndView mav = new ModelAndView("index");
      mav.addObject("user", new User());
      mav.addObject("content", "login");
      return mav;
   }

   @PostMapping({"/", "/login"})
   public String submitLogin(@ModelAttribute User user, Model model) throws InvalidLoginException {

      User checkUser;
      checkUser = userService.getUserByEmail(user.getEmail());

      String password = user.getPassword();

      if (password.equals(checkUser.getPassword())) {
         model.addAttribute("content", "landing");
         model.addAttribute("email", checkUser.getEmail());
      } else {
         throw new InvalidLoginException(user.getEmail());
      }

      return "index";
   }

   @GetMapping("/register")
   public String regForm(Model model) {
      model.addAttribute("user", new User());
      model.addAttribute("content", "register");
      model.addAttribute("confirmPassword", null);
      return "index";
   }

   @PostMapping("/register")
   public ModelAndView register( @ModelAttribute User newUser,
                                 @ModelAttribute String confirmPassword) {

      ModelAndView mav = new ModelAndView("index");

      User userExists = userService.getUserByEmail(newUser.getEmail());


      mav.addObject("content", "login");
      return "index";
   }
}
