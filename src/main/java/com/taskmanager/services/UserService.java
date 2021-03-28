package com.taskmanager.services;

import com.taskmanager.exceptions.InvalidLoginException;
import com.taskmanager.model.User;
import com.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepo;

   public List<User> getAllUsers() {
      return userRepo.findAll();
   }

   public User getUserByEmail(String email) throws InvalidLoginException {
      Optional<User> foundUser = Optional.ofNullable(userRepo.findByEmail(email));

      return foundUser.orElseGet(User::new);
   }

   public User updateUser(User updateUser) {
      return userRepo.save(updateUser);
   }
}
