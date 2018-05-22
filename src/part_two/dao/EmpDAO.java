package part_two.dao;

import part_two.beans.Dept;
import part_two.beans.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static part_two.dao.DAOUtility.silentCloses;

public class EmpDAO extends DAO<Emp> {
    DAOFactory daoFactory;

    public EmpDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Emp find(int t) {
        Connection        connexion         = null;
        PreparedStatement preparedStatement = null;
        ResultSet         resultSet         = null;
        ResultSet         resultSetDept     = null;

        Emp  emp  = new Emp();
        Dept dept = new Dept();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
            preparedStatement.setInt(1, t);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                emp.setEmpno(resultSet.getLong("empno"));
                emp.setEname(resultSet.getString("ename"));
                emp.setEfirst(resultSet.getString("efirst"));
                emp.setJob(resultSet.getString("job"));
                emp.setHiredate(resultSet.getDate("hiredate"));
                emp.setSal(resultSet.getInt("sal"));
                emp.setComm(resultSet.getInt("comm"));
                emp.setTel(resultSet.getInt("tel"));
                emp.setMgr(null);

                preparedStatement = connexion.prepareStatement("SELECT * FROM DEPT WHERE DEPTNO = ?");
                preparedStatement.setInt(1, Integer.parseInt(resultSet.getString("mgr")));
                resultSetDept = preparedStatement.executeQuery();

                while(resultSetDept.next()) {
                    System.out.println(resultSetDept.getLong("deptno"));
                    System.out.println(resultSetDept.getLong("dname"));
                    dept.setDeptno(resultSetDept.getLong("deptno"));
                    dept.setDname(resultSetDept.getString("dname"));
                    dept.setLoc(resultSetDept.getString("loc"));
                }

                emp.setDeptno(dept);
            }

            /*preparedStatement = connexion.prepareStatement("SELECT * FROM MGR WHERE EMPNO = ?");*/
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentCloses(resultSet, preparedStatement, connexion);
        }
        return emp;
    }

    @Override
    public boolean create(Emp object) {
        return false;
    }

    @Override
    public boolean update(Emp object) {
        return false;
    }

    @Override
    public boolean delete(Emp object) {
        return false;
    }
}
