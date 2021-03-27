package com.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
   private Integer id;

   @Column
   @NotNull
   private String name;

   @Column
   private String severity;

   @Column
   private String description;

   @Column
   private String email;

   @ManyToOne
   private User user;

   public Task() {}

   public Task(String name) {
      this.name = name;
   }

   public Task(String name, String severity, String description, String email, User user) {
      this.name = name;
      this.severity = severity;
      this.description = description;
      this.email = email;
      this.user = user;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSeverity() {
      return severity;
   }

   public void setSeverity(String severity) {
      this.severity = severity;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}
