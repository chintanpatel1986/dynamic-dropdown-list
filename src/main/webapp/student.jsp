<%@ page import="org.chintanpatel.app.dao.CollegeDao" %>
<%@ page import="java.util.List" %>
<%@ page import="org.chintanpatel.app.bean.College" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chintanpatel
  Date: 24/01/24
  Time: 1:20 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script type="text/javascript" href="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" href="js/bootstrap.min.js"></script>
</head>
<%
    CollegeDao collegeDao = new CollegeDao();
    List<College>collegeList = collegeDao.getAllCollegeList();
    request.setAttribute("collegeList",collegeList);
%>
<body>
<div class="container mt-5 border border-1">
    <div class="container">
        <div class="fs-1 fw-bold">
            <h1>Manage Student</h1>
        </div>
        <form action="StudentController" method="post" class="row g-3">
            <input type="hidden" name="studentId" value="${student.studentId}">
            <div class="col-6">
                <label class="form-label fw-bold">FirstName</label>
                <input type="text" name="firstName" class="form-control" value="${student.firstName}">
            </div>
            <div class="col-6">
                <label class="form-label fw-bold">LastName</label>
                <input type="text" name="lastName" class="form-control" value="${student.lastName}">
            </div>
            <div class="col-6">
                <label class="form-label fw-bold">E-Mail</label>
                <input type="email" name="email" class="form-control" value="${student.email}">
            </div>
            <div class="col-6">
                <label class="form-label fw-bold">Mobile</label>
                <input type="text" name="mobile" class="form-control" value="${student.mobile}">
            </div>
            <div class="col-12">
                <label class="form-label fw-bold">BirthDate</label>
                <input type="date" name="birthDate" class="form-control" value="${student.birthDate}">
            </div>
            <div class="col-4">
                <label class="form-label fw-bold">UserName</label>
                <input type="text" name="userName" class="form-control" value="${student.userName}">
            </div>
            <div class="col-4">
                <label class="form-label fw-bold">Password</label>
                <input type="password" name="password" class="form-control" value="${student.password}">
            </div>
            <div class="col-4">
                <label class="form-label fw-bold">College</label>
                <select id="collegeId" name="collegeId" class="form-select">
                    <c:choose>
                        <c:when test="${student.collegeId > 0}">
                            <option value="-1" id="selected">----------Select College----------</option>
                            <c:forEach items="${collegeList}" var="college">
                                <c:choose>
                                    <c:when test="${college.collegeId == student.collegeId}">
                                        <option value="${college.collegeId}" selected="selected">${college.collegeName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${college.collegeId}">${college.collegeName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <option value="-1" label="---select---" selected="selected">----------Select College----------</option>
                            <c:forEach items="${collegeList}" var="college">
                                <c:choose>
                                    <c:when test="${college.collegeId == student.collegeId}">
                                        <option value="${college.collegeId}" selected="selected">${college.collegeName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${college.collegeId}">${college.collegeName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
            <div class="col-12 d-grid gap-2">
                <input type="submit" value="Submit" class="btn btn-success">
            </div>
        </form>
    </div>
    <div class="container">
        <c:if test="${!empty studentList}">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>E-Mail</th>
                    <th>Mobile</th>
                    <th>UserName</th>
                    <th>Collage</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${studentList}" var="student">
                    <tr>
                        <td><c:out value="${student.firstName}"/></td>
                        <td><c:out value="${student.lastName}"/></td>
                        <td><c:out value="${student.email}"/></td>
                        <td><c:out value="${student.mobile}"/></td>
                        <td><c:out value="${student.userName}"/></td>
                        <td><c:out value="${student.collegeName}"/></td>
                        <td>
                            <a href="StudentController?action=edit&studentId=${student.studentId}" class="link-primary">Edit</a>
                            &nbsp;|&nbsp;
                            <a href="StudentController?action=delete&studentId=${student.studentId}" class="link-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
<div class="container mt-5">
    <a href="index.jsp" class="link-primary fs-4">Back To Home</a>
</div>
</body>
</html>
