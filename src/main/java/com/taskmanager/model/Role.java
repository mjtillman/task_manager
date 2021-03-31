package com.taskmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="role_id")
   private Integer id;

   @Column
   private String role;

   public Integer getId() {
      return id;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
