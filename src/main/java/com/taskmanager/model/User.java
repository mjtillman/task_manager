package com.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="user_id")
   private Integer id;

   @Column
   @NotNull @Email
   private String username;

   @Column
   @NotNull
   private String password;

   @ManyToMany(cascade = CascadeType.MERGE)
   @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;

   public User() {}

   public User(final String username, final String password) {
      this.username = username;
      this.password = password;
   }

   public Integer getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
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

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
}
