package part_two.dao;

import part_two.beans.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static part_two.dao.DAOUtility.silentCloses;

public class DeptDAO extends DAO<Dept> {
    private DAOFactory daoFactory;

    public DeptDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Dept find(int t) {
        Connection        connexion         = null;
        PreparedStatement preparedStatement = null;
        ResultSet         resultSet         = null;

        Dept dept = new Dept();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM DEPT WHERE DEPTNO = ?");
            preparedStatement.setInt(1, t);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dept.setDeptno(resultSet.getLong("deptno"));
                dept.setDname(resultSet.getString("dname"));
                dept.setLoc(resultSet.getString("loc"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentCloses(resultSet, preparedStatement, connexion);
        }
        return dept;
    }

    @Override
    public boolean create(Dept object) {
        return false;
    }

    @Override
    public boolean update(Dept object) {
        return false;
    }

    @Override
    public boolean delete(Dept object) {
        return false;
    }
}
