package org.chintanpatel.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chintanpatel.app.dao.StudentDao;
import org.chintanpatel.app.domain.Student;

import java.io.IOException;

@WebServlet(name = "StudentController", value = "/StudentController")
public class StudentController extends HttpServlet {
    private static final String INSERT_OR_UPDATE = "student.jsp";
    private static final String STUDENT_LIST = "student.jsp";
    private static final String ERROR = "error.jsp";
    private final StudentDao studentDao;

    public StudentController() {
        studentDao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward;
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = STUDENT_LIST;
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            boolean isDelete = studentDao.deleteStudentById(studentId);
                if (isDelete) {
                    req.setAttribute("studentList",studentDao.getAllStudentList());
                } else {
                    req.getRequestDispatcher(ERROR).include(req,resp);
                }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            Student student = studentDao.findStudentById(studentId);
            req.setAttribute("student",student);
            req.setAttribute("studentList",studentDao.getAllStudentList());
        }
        else {
            forward = INSERT_OR_UPDATE;
            req.setAttribute("studentList", studentDao.getAllStudentList());
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = new Student();
        student.setFirstName(req.getParameter("firstName"));
        student.setMiddleName(req.getParameter("middleName"));
        student.setLastName(req.getParameter("lastName"));
        student.setAddress(req.getParameter("address"));
        student.setEmail(req.getParameter("email"));
        student.setMobile(Long.parseLong(req.getParameter("mobile")));
        student.setUserName(req.getParameter("userName"));
        student.setPassword(req.getParameter("password"));
        student.setCollegeId(Integer.parseInt(req.getParameter("collegeId")));
        String studentId = req.getParameter("studentId");

        if (studentId == null || studentId.isEmpty()) {
            boolean isInsert = studentDao.insertStudent(student);
                if (isInsert) {
                    RequestDispatcher view = req.getRequestDispatcher(STUDENT_LIST);
                    req.setAttribute("studentList",studentDao.getAllStudentList());
                    view.forward(req,resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req,resp);
                }
        } else {
            student.setStudentId(Integer.parseInt(studentId));
            boolean isUpdate = studentDao.updateStudent(student);
                if (isUpdate) {
                    RequestDispatcher view = req.getRequestDispatcher(STUDENT_LIST);
                    req.setAttribute("studentList",studentDao.getAllStudentList());
                    view.forward(req,resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req,resp);
                }
        }
    }
}
