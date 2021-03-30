package com.taskmanager.services;

import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.model.User;
import com.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

   @Autowired
   BCryptPasswordEncoder encoder;

   @Autowired
   private UserRepository userRepo;

   public List<User> getAllUsers() {
      return userRepo.findAll();
   }

   public User getUserByUsername(String email) throws InvalidLoginException {
      Optional<User> foundUser = Optional.ofNullable(userRepo.findByUsername(email));

      return foundUser.orElseGet(User::new);
   }

   public void saveUser(User user) {
      user.setPassword(encoder.encode(user.getPassword()));
      userRepo.save(user);
   }
}
