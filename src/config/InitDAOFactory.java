package config;

import part_two.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitDAOFactory implements ServletContextListener {
    private static final String ATT_DAO = "daofactory";

    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        this.daoFactory = DAOFactory.getInstance();

        servletContext.setAttribute(ATT_DAO, this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
