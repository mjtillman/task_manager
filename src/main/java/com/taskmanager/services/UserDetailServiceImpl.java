package com.taskmanager.services;

import com.taskmanager.model.Role;
import com.taskmanager.model.User;
import com.taskmanager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

   @Autowired
   private RoleRepository roleRepo;

   @Autowired
   private UserService userService;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String userName) {
      User user = userService.getUserByUsername(userName);
      List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
      return buildUserForAuthentication(user, authorities);
   }

   private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
      Set<GrantedAuthority> roles = new HashSet<>();

         for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
         }

      return new ArrayList<>(roles);
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
