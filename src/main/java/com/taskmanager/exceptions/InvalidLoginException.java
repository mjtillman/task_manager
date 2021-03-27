package com.taskmanager.exceptions;

public class InvalidLoginException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   private String email = "";

   public InvalidLoginException() {
      super();
   }

   public InvalidLoginException(String email) {
      super();
      this.email = email;
   }

   public String getEmail() {
      return email;
   }
}
