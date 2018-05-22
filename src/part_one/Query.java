package part_one;

import part_two.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static part_two.dao.DAOUtility.silentClose;
import static part_two.dao.DAOUtility.silentCloses;

public class Query {
    private DAOFactory daoFactory;

    public Query(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Map<String, String>> displayDepartment() throws SQLException {
        List<Map<String, String>> departments = new ArrayList<>();

        Connection connexion   = this.daoFactory.getConnection();
        Statement  statement   = connexion.createStatement();
        ResultSet  resultatSet = statement.executeQuery("SELECT DEPTNO, DNAME, LOC FROM DEPT");

        while (resultatSet.next()) {
            HashMap<String, String> department = new HashMap<>();
            departments.add(department);

            department.put("deptno", resultatSet.getString("deptno"));
            department.put("dname", resultatSet.getString("dname"));
            department.put("loc", resultatSet.getString("loc"));

        }
        silentCloses(statement, connexion);

        return departments;
    }


    public String moveDepartment(int empno, int newDeptno) throws SQLException {
        Connection        connexion         = this.daoFactory.getConnection();
        PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE EMP SET DEPTNO = ? WHERE EMPNO = ?");

        preparedStatement.setInt(1, newDeptno);
        preparedStatement.setInt(2, empno);

        int result = preparedStatement.executeUpdate();
        silentCloses(preparedStatement, connexion);

        return (result == 1) ? "Successfully Updated" : "Error, try Again";
    }


    public String displayTable(String tableName) throws SQLException {
        Connection    connexion     = this.daoFactory.getConnection();
        Statement     statement     = connexion.createStatement();
        StringBuilder stringBuilder = new StringBuilder();
        List<String>  columnNames   = new ArrayList<>();

        tableName = tableName.toUpperCase();
        ResultSet columnNamesQuery = statement.executeQuery("SELECT column_name from user_tab_cols WHERE table_name = '" + tableName + "'");

        // add column names in arrayList
        while (columnNamesQuery.next()) {
            columnNames.add(columnNamesQuery.getString("column_name"));
        }
        silentClose(columnNamesQuery);

        // add column names to the string builder (first row)
        for (String column : columnNames) {
            stringBuilder.append(column + " | ");
        }
        stringBuilder.append("<br>");

        ResultSet resultatTable = statement.executeQuery("SELECT * FROM " + tableName);

        // add data to string builder
        while (resultatTable.next()) {
            for (String column : columnNames) {
                stringBuilder.append(resultatTable.getString(column) + " | ");
            }
            stringBuilder.append("<br>");
        }
        silentClose(resultatTable);

        return stringBuilder.toString();
    }
}
