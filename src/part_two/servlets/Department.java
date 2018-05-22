package part_two.servlets;

import part_two.beans.Dept;
import part_two.dao.DAOFactory;
import part_two.dao.DeptDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Department extends HttpServlet {
    private static final String VIEW_DEPARTMENT = "/WEB-INF/part_two/department.jsp";

    private static final String DAO_FACTORY     = "daofactory";
    private static final String ATT_DEPARTMENT  = "department-id";
    private static final String DEPARTMENT      = "department";


    private DeptDAO deptDAO;

    @Override
    public void init() {
        this.deptDAO = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY)).getDeptDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter(ATT_DEPARTMENT);
        Dept   dept;

        dept = this.deptDAO.find(Integer.parseInt(deptno));

        req.setAttribute(DEPARTMENT, dept);

        this.getServletContext().getRequestDispatcher(VIEW_DEPARTMENT).forward(req, resp);
    }
}
