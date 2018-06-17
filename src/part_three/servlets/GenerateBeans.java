package part_three.servlets;

import part_three.QueryThirdPart;
import part_two.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class GenerateBeans extends HttpServlet {
    private static final String VIEW_GENERATE_BEANS = "/WEB-INF/part_three/generateBeans.jsp";
    private static final String DAO_FACTORY         = "daofactory";
    private static final String TABLES              = "tables";
    private static final String ERRORS              = "errors";

    private QueryThirdPart queryThirdPart;



    @Override
    public void init() {
        this.queryThirdPart = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY)).getQueryThirdPart();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String>                             errors        = new ArrayList<>();
        HashMap<String, HashMap<String, String>> tables        = new HashMap<>();

        try {
            tables = queryThirdPart.displayAllTable();
        } catch (SQLException e) {
            errors.add(e.getMessage());
        }

        tables = createBeans(tables, req);

        req.setAttribute(TABLES, tables);
        req.setAttribute(ERRORS, errors);


        req.getServletContext().getRequestDispatcher(VIEW_GENERATE_BEANS).forward(req, resp);
    }

    public HashMap<String, HashMap<String, String>> createBeans(HashMap<String, HashMap<String, String>> tables, HttpServletRequest req) {
        ServletContext servletContext = getServletContext();
        String         contextPath    = servletContext.getRealPath(File.separator);
        String         PATH           = contextPath + "../../../src/part_two/beans/";
        HashMap<String, HashMap<String, String>> createdTables = new HashMap<>();

        for (Map.Entry<String, HashMap<String, String>> entry : tables.entrySet()) {
            String className = toUpperCamelCase(entry.getKey());
            if (!isClass("part_two.beans." + className)) {
                createdTables.put(entry.getKey(), entry.getValue());

                PrintWriter writer;
                try {
                    writer = new PrintWriter(PATH + className + ".java", "UTF-8");
                    writer.println("package part_two.beans;");
                    writer.println();
                    writer.println("public class " + className + " {");
                    Iterator iterator = entry.getValue().entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry pair = (Map.Entry) iterator.next();
                        buildAttribute(writer, pair.getKey().toString(), pair.getValue().toString());
                    }

                    writer.println();

                    Iterator iteratorAttribute = entry.getValue().entrySet().iterator();
                    while (iteratorAttribute.hasNext()) {
                        Map.Entry pair = (Map.Entry) iteratorAttribute.next();
                        buildAttributeGetter(writer, pair.getKey().toString(), pair.getValue().toString());
                        buildAttributeSetter(writer, pair.getKey().toString(), pair.getValue().toString());
                    }

                    writer.println("}");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return createdTables;
    }

    // verifie que la classe existe
    public boolean isClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    static void buildAttribute(PrintWriter writer, String attribute, String type) {
        HashMap<String, String> types = dataType();
        writer.println("    private " + types.get(type) + " " + toLowerCamelCase(attribute) + ";");
    }

    static void buildAttributeSetter(PrintWriter writer, String attribute, String type) {
        HashMap<String, String> types                = dataType();
        String                  formatAttributeUpper = toUpperCamelCase(attribute);
        String                  formatAttributeLower = toLowerCamelCase(attribute);
        String                  dataType             = types.get(type);

        writer.println("    public void set" + formatAttributeUpper + "(" + dataType + " " + formatAttributeLower + ") " +
                "{ this." + formatAttributeLower + " = " + formatAttributeLower + "; }");
    }

    static void buildAttributeGetter(PrintWriter writer, String attribute, String type) {
        HashMap<String, String> types                = dataType();
        String                  formatAttributeUpper = toUpperCamelCase(attribute);
        String                  formatAttributeLower = toLowerCamelCase(attribute);
        String                  dataType             = types.get(type);

        writer.println("    public void get" + formatAttributeUpper + "(" + dataType + " " + formatAttributeLower + ") " +
                "{ this." + formatAttributeLower + " = " + formatAttributeLower + "; }");
    }

    static HashMap<String, String> dataType() {
        HashMap<String, String> types = new HashMap<>();
        types.put("DATE", "Date");
        types.put("VARCHAR2", "String");
        types.put("NUMBER", "Long");

        return types;
    }

    // UTILE METHOD
    static String toUpperCamelCase(String s) {
        s = s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
        int pos = s.indexOf("_");
        if (pos > 0) {
            s = s.substring(0, pos) + (s.substring(pos + 1).substring(0, 1).toUpperCase() +
                    s.substring(pos + 1).substring(1).toLowerCase());
        }
        return s;
    }

    static String toLowerCamelCase(String s) {
        s = s.toLowerCase();
        int pos = s.indexOf("_");
        if (pos > 0) {
            s = s.substring(0, pos) + (s.substring(pos + 1).substring(0, 1).toUpperCase() +
                    s.substring(pos + 1).substring(1).toLowerCase());
        }
        return s;
    }


}
