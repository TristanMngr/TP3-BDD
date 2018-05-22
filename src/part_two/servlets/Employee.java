package part_two.servlets;

import part_two.beans.Emp;
import part_two.dao.DAOFactory;
import part_two.dao.EmpDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Employee extends HttpServlet {
    private static final String VIEW_EMPLOYEE = "/WEB-INF/part_two/employee.jsp";

    private static final String DAO_FACTORY  = "daofactory";
    private static final String ATT_EMPLOYEE = "employee-id";
    private static final String EMPLOYEE     = "employee";


    private EmpDAO empDAO;

    @Override
    public void init() {
        this.empDAO = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY)).getEmpDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employee = req.getParameter(ATT_EMPLOYEE);
        Emp    emp;

        emp = this.empDAO.find(Integer.parseInt(employee));

        System.out.println(emp.getEname());
        req.setAttribute(EMPLOYEE, emp);

        this.getServletContext().getRequestDispatcher(VIEW_EMPLOYEE).forward(req, resp);
    }
}
