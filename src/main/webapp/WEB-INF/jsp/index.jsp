<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta charset="UTF-8">
   <title>Task Manager - Welcome</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<nav th:fragment="navigation" class="navbar navbar-light bg-light">
   <div class="container-fluid">
      <a class="navbar-brand" href="index.jsp">Task Manager</a>
      <ul class="nav">
         <li class="nav-item">
            <a class="nav-link active" href="index.jsp">Login</a>
         </li>
         <li class="nav-item">
            <a class="nav-link" href="register.jsp">Register</a>
         </li>
      </ul>
   </div>
</nav>
<main class="row">
   <div class="col-3"></div
   <div class="col-6">
      <div class="container-fluid pt-5">
         <h3 class="mb-3">Login</h3>
         <div th:if="${regSuccess == true}" class="mb-3">
            Registration successful, please log in.
         </div>
         <div th:if="${loginErr == true}" class="mb-3">
            <em>Invalid credentials, please try again.</em>
         </div>
         <div th:if="${regSuccess == true}" class="mb-3">
            <em>Registration successful, please log in.</em>
         </div>
         <form action="/" th:action="@{/}" th:object="${user}"
               method="POST" id="login">
            <div class="mb-4">
               <label for="email" class="form-label">Email address</label>
               <input type="email" th:field="*{username}"
                      class="form-control" id="email" />
            </div>
            <div class="mb-3">
               <label for="password" class="form-label">Password</label>
               <input type="password" th:field="*{password}" class="form-control" id="password" />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
         </form>
      </div>
   </div>
   <div class="col-3"></div>

   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</main>
</html>