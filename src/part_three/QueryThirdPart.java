package part_three;

import part_two.dao.DAOFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static part_two.dao.DAOUtility.silentCloses;

public class QueryThirdPart {
    private DAOFactory daoFactory;

    public QueryThirdPart(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public HashMap<String, HashMap<String, String>> displayAllTable() throws SQLException {
        HashMap<String, HashMap<String, String>> tables = new HashMap<>();
        HashMap<String, String> column = new HashMap<>();
        String tableName = "";

        Connection connexion   = this.daoFactory.getConnection();
        Statement  statement   = connexion.createStatement();
        ResultSet  resultatSet = statement.executeQuery("SELECT * FROM all_tab_columns WHERE OWNER = 'TRISTAN'");

        while (resultatSet.next()) {
            String newTableName = resultatSet.getString("TABLE_NAME");

            if (!newTableName.equals(tableName)) {
                column = new HashMap<>();
                tableName = newTableName;
            }
            column.put(resultatSet.getString("COLUMN_NAME"), resultatSet.getString("DATA_TYPE"));
            tables.put(tableName, column);
        }
        silentCloses(statement, connexion);

        return tables;
    }
}
