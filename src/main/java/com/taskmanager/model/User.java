package com.taskmanager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private Long id;

   @Column
   @NotNull @Email
   private String username;

   @Column
   @NotNull
   private String password;

   public User() {}

   public User(final String username, final String password) {
      this.username = username;
      this.password = password;
   }

   public Long getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public void setEmail(String email) {
      this.username = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public List<GrantedAuthority> getRoles() {
      return new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority("USER")));
   }
}
