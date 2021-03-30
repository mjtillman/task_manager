<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <title>Task Manager - Welcome</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<nav class="navbar navbar-light bg-light">
   <div class="container-fluid">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Task Manager</a>
      <ul class="nav">
         <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/index">Login</a>
         </li>
         <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
         </li>
      </ul>
   </div>
</nav>
<main class="row">
   <div class="col-3"></div>
   <div class="col-6">
      <div class="container-fluid pt-5">
         <h3 class="mb-3">Login</h3>
         <form:form action="${pageContext.request.contextPath}/tasks/view_tasks" method="POST" id="login" modelAttribute="user">
            <div class="mb-4">
               <form:label path="username" class="form-label">Email address</form:label>
               <form:input path="username" class="form-control" />
            </div>
            <div class="mb-3">
               <form:label path="password" class="form-label">Password</form:label>
               <form:input path="password" class="form-control" type="password" />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
         </form:form>
      </div>
   </div>
   <div class="col-3"></div>

   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</main>
</html>