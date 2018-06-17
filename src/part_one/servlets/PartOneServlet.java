package part_one.servlets;

import part_one.QueryFirstPart;
import part_two.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Nous avons décidé d'implémenter toute la partie I dans ce servlet, ce n'est pas très
 * propre, mais nous pensons que c'est suffisant pour cette partie.
 */
public class PartOneServlet extends HttpServlet {
    private static final String VIEW_DEPARTMENT = "/WEB-INF/part_one/department.jsp";
    private static final String VIEW_EMPLOYEE   = "/WEB-INF/part_one/employee.jsp";
    private static final String VIEW_TABLE      = "/WEB-INF/part_one/table.jsp";

    private static final String LIST_DEPARTMENT = "departments";
    private static final String MESSAGE_UPDATE  = "update";
    private static final String TABLE_IN_FORM   = "table";
    private static final String ERRORS          = "errors";

    private static final String ATT_DISPATCH    = "dispatch";
    private static final String ATT_EMPLOYEE    = "employee";
    private static final String ATT_DEPARTMENT  = "department";
    private static final String ATT_TABLE       = "table";

    private static final String DAO_FACTORY         = "daofactory";

    private QueryFirstPart queriesFirstPart;

    @Override
    public void init() {
        this.queriesFirstPart = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY)).getQueryFirstPart();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String       dispatching = req.getParameter(ATT_DISPATCH);
        List<String> errors      = new ArrayList<>();
        String       view        = null;

        switch (dispatching) {
            case "display-department":
                initForDepartment(req, errors);
                view = VIEW_DEPARTMENT;
                break;
            case "move-employee":
                initForMoveEmployee(req, errors);
                view = VIEW_EMPLOYEE;
                break;
            case "display-table":
                initForDisplayTable(req, errors);
                view = VIEW_TABLE;
                break;
        }

        this.getServletContext().getRequestDispatcher(view).forward(req, resp);
    }

    private void initForDepartment(HttpServletRequest req, List<String> errors) {
        List<Map<String, String>> departments = new ArrayList<>();

        try {
            departments = queriesFirstPart.displayDepartment();
        } catch (SQLException e) {
            errors.add(e.getMessage());
        }

        req.setAttribute(ERRORS, errors);
        req.setAttribute(LIST_DEPARTMENT, departments);
    }

    private void initForMoveEmployee(HttpServletRequest req, List<String> errors) {
        int employee   = Integer.parseInt(req.getParameter(ATT_EMPLOYEE));
        int department = Integer.parseInt(req.getParameter(ATT_DEPARTMENT));

        try {
            req.setAttribute(MESSAGE_UPDATE, queriesFirstPart.moveDepartment(employee, department));
        } catch (SQLException e) {
            errors.add(e.getMessage());
        }

        req.setAttribute(ERRORS, errors);
    }

    private void initForDisplayTable(HttpServletRequest req, List<String> errors) {
        String tableName = req.getParameter(ATT_TABLE);

        String tableInForm = null;

        try {
            tableInForm = queriesFirstPart.displayTable(tableName);
        } catch (SQLException e) {
            errors.add(e.getMessage());
        }

        req.setAttribute(TABLE_IN_FORM, tableInForm);
    }
}
