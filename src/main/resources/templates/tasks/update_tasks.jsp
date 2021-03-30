<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
   <meta charset="UTF-8">
   <title>Task Manager - Welcome</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<nav th:insert="fragments/fragments :: navigation"></nav>
<main class="row">
   <div class="col-3"></div>
   <div class="col-6">
      <div class="container-fluid pt-5">
         <h3 class="mb-3">Add Tasks</h3>
         <div th:if="${tasks.isEmpty()}">
            <em>You have no tasks to remove.</em>
         </div>
         <div th:if="${!tasks.isEmpty()}">
            <table class="table">
               <thead>
               <tr>
                  <th></th>
                  <th>Severity</th>
                  <th>Task</th>
                  <th>Description</th>
               </tr>
               </thead>
               <form>
                  <tbody th:each="task: ${tasks}">
                  <tr>
                     <td><input type="checkbox" /></td>
                     <td><span th:text="${task.getSeverity()}"></span></td>
                     <td><span th:text="${task.getName()}"></span></td>
                     <td><span th:text="${task.getDescription()}"></span></td>
                  </tr>
                  </tbody>
               </form>
            </table>
         </div>
      </div>
   </div>
   <div class="col-3"></div>

   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</main>
</html>