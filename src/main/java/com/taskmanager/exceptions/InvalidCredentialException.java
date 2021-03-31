package com.taskmanager.exceptions;

public class InvalidCredentialException extends RuntimeException {
   private final String email;

   public InvalidCredentialException(String email) {
      super();
      this.email = email;
   }

   public String getEmail() {
      return email;
   }
}
