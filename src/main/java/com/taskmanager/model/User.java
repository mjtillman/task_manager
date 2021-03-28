package com.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private Long id;

   @Column
   @NotNull @Email
   private String email;

   @Column
   @NotNull
   private String password;

   public User() {}

   public User(final String email, final String password) {
      this.email = email;
      this.password = password;
   }

   public Long getId() {
      return id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
