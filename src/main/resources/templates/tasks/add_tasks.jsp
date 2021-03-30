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
         <form action="/add_task" th:action="@{/add_task}" th:object="${newTask}"
               method="POST" id="addtask">
            <div class="row mb-3">
               <div class="col">
                  <label for="taskName" class="form-label">Email address</label>
                  <input type="text" th:field="*{name}" class="form-control"
                         id="taskName" />
               </div>
            </div>
            <div class="row mb-3">
               <div class="col">
                  <label for="severity" class="form-label">Severity</label>
                  <select th:field="*{severity}" class="form-control" id="severity">
                     <option value="Low">Low</option>
                     <option value="Medium">Medium</option>
                     <option value="High">High</option>
                  </select>
               </div>
            </div>
            <div class="row mb-3">
               <div class="col-6">
                  <label for="start_date" class="form-label">Start Date</label>
                  <input type="date" id="start_date" />
               </div>
               <div class="col-6">
                  <label for="end_date" class="form-label">End Date</label>
                  <input type="date" id="end_date" />
               </div>
            </div>
            <div class="row mb-3">
               <div class="col">
                  <label for="task_description" class="form-label">Description</label>
                  <input type="text" th:field="*{description}" class="form-control" id="task_description" />
               </div>
            </div>
            <div class="row mb-3">
               <div class="col">
                  <input type="hidden" th:field="*{email}" value="userEmail" th:value="${userEmail}" id="userEmail" />
                  <button type="submit" class="btn btn-primary">Submit</button>
               </div>
            </div>
         </form>
      </div>
   </div>
   <div class="col-3"></div>
   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</main>
</html>