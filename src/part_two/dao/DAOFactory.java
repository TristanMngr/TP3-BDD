package part_two.dao;

import part_one.QueryFirstPart;
import part_three.QueryThirdPart;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    private static final String FICHIER_PROPERTIES = "part_two/dao/dao.properties";
    private static final String PROPERTY_URL       = "url";
    private static final String PROPERTY_DRIVER    = "driver";
    private static final String PROPERTY_USER      = "user";
    private static final String PROPERTY_PASSWORD  = "password";

    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigException {
        Properties properties = new Properties();
        String     url;
        String     driver;
        String     user;
        String     password;

        ClassLoader classLoader       = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOConfigException("Le fichier dao.properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            user = properties.getProperty(PROPERTY_USER);
            password = properties.getProperty(PROPERTY_PASSWORD);
        } catch (IOException e) {
            throw new DAOConfigException("Impossible de charger le fichier dao.properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigException("Le driver est introuvable dans le classpath. " + driver, e);
        }

        DAOFactory instance = new DAOFactory(url, user, password);
        return instance;
    }

    /* Connexion à la BDD */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public QueryFirstPart getQueryFirstPart() {
        return new QueryFirstPart(this);
    }

    public QueryThirdPart getQueryThirdPart() {
        return new QueryThirdPart(this);
    }

    public DeptDAO getDeptDAO() {
        return new DeptDAO(this);
    }

    public EmpDAO getEmpDAO() {
        return new EmpDAO(this);
    }
}
