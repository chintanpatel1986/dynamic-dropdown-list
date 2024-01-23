<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chintanpatel
  Date: 23/01/24
  Time: 2:39 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>College Management</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" href="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" href="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-5 border border-1">
  <div class="container">
    <div class="fs-1 fw-bold">
      <h1>Manage Collage</h1>
    </div>
    <form action="CollegeController" method="post" class="row g-3">
      <input type="hidden" name="collegeId" value="${college.collegeId}">
      <div class="col-12 mt-5">
        <label class="form-label fw-bold">Collage</label>
        <input type="text" name="collegeName" class="form-control" value="${college.collegeName}">
      </div>
      <div class="col-12 d-grid gap-2">
        <input type="submit" value="Submit" class="btn btn-success">
      </div>
    </form>
  </div>
  <div class="container">
    <c:if test="${!empty collegeList}">
      <table class="table table-striped table-bordered">
        <thead>
        <tr>
          <th>Collage</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${collegeList}" var="college">
          <tr>
            <td><c:out value="${college.collegeName}"/></td>
            <td>
              <a href="CollegeController?action=edit&collegeId=${college.collegeId}" class="link-primary">Edit</a>
              &nbsp;|&nbsp;
              <a href="CollegeController?action=delete&collegeId=${college.collegeId}" class="link-danger">Delete</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:if>
  </div>
</div>
<div class="container mt-5">
  <a href="index.jsp" class="link-primary fs-4">Back to Home</a>
</div>
</body>
</html>
