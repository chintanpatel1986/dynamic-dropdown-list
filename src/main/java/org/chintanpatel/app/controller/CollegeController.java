package org.chintanpatel.app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chintanpatel.app.bean.College;
import org.chintanpatel.app.dao.CollegeDao;

import java.io.IOException;

@WebServlet(name = "CollegeController", value = "/CollegeController")
public class CollegeController extends HttpServlet {
    private static final String INSERT_OR_UPDATE = "college.jsp";
    private static final String COLLEGE_LIST = "college.jsp";
    private static final String ERROR = "error.jsp";
    private final CollegeDao collegeDao;

    public CollegeController() {
        collegeDao = new CollegeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward;
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = COLLEGE_LIST;
            int collegeId = Integer.parseInt(req.getParameter("collegeId"));
            boolean isDelete = collegeDao.deleteCollegeById(collegeId);
                    if (isDelete) {
                        req.setAttribute("collegeList",collegeDao.getAllCollegeList());
                    } else {
                        req.getRequestDispatcher(ERROR).include(req,resp);
                    }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_UPDATE;
            int collegeId = Integer.parseInt(req.getParameter("collegeId"));
            College college = collegeDao.findCollegeById(collegeId);
            req.setAttribute("college",college);
            req.setAttribute("collegeList",collegeDao.getAllCollegeList());
        }
        else {
            forward = INSERT_OR_UPDATE;
            req.setAttribute("collegeList",collegeDao.getAllCollegeList());
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        College college = new College();
        college.setCollegeName(req.getParameter("collegeName"));
        String collegeId = req.getParameter("collegeId");

        if (collegeId == null || collegeId.isEmpty()) {
            boolean isInsert = collegeDao.insertCollegeDetails(college);
                if (isInsert) {
                    RequestDispatcher view = req.getRequestDispatcher(COLLEGE_LIST);
                    req.setAttribute("collegeList",collegeDao.getAllCollegeList());
                    view.forward(req,resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req,resp);
                }
        } else {
            college.setCollegeId(Integer.parseInt(collegeId));
            boolean isUpdate = collegeDao.updateCollegeDetails(college);
                if (isUpdate) {
                    RequestDispatcher view = req.getRequestDispatcher(COLLEGE_LIST);
                    req.setAttribute("collegeList",collegeDao.getAllCollegeList());
                    view.forward(req,resp);
                } else {
                    req.getRequestDispatcher(ERROR).include(req,resp);
                }
        }
    }
}
