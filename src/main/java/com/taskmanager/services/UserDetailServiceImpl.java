package com.taskmanager.services;

import com.taskmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

   @Autowired
   private UserService userService;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String email) {
      User user = userService.getUserByEmail(email);
      List<GrantedAuthority> authorities = user.getRoles();
      return buildUserForAuthentication(user, authorities);
   }

   private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            authorities);
   }
}
