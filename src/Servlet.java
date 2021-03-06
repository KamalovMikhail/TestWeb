import control.connect.Connect;
import control.object.Place;
import control.object.Zakaz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mikhail on 07.03.15.
 */
@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Matcher m1 = p.matcher(login);
        Matcher m2 = p.matcher(login);

        if (m1.matches() && m2.matches()) {
            String query1 = "SELECT * FROM project.user where user.login = '" + login + "';";
            String query2 = "SELECT * FROM project.user where user.login = '" + login + "' and user.password='" + pass + "';";
            try {
                ResultSet resultSet = getResultSet(query1);
                if (!resultSet.next()) {
                    request.setAttribute("err", "Такого пользователя не существует");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

                    if (dispatcher != null) {

                        dispatcher.forward(request, response);

                    }
                } else {
                    ResultSet resultSet2 = getResultSet(query2);
                    if (!resultSet2.next()) {
                        request.setAttribute("err", "Вы неправильно ввели пароль");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

                        if (dispatcher != null) {

                            dispatcher.forward(request, response);

                        }
                    } else {


                        if (resultSet2.getString("status").equals("0")) {
                            ResultSet resultSet3 = getResultSet("Select * from project.place");

                            List<Place> places = new LinkedList<Place>();
                            String idu = resultSet2.getString("iduser");
                            while (resultSet3.next()) {
                                places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));

                            }

                            List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
                            ResultSet resultSet4 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = " + Integer.valueOf(idu) + " and zakaz.status = 'action' and zakaz.idplace=place.idplace");

                            while (resultSet4.next()) {
                                zakazs1.add(new Zakaz(resultSet4.getString("nameplace"), resultSet4.getString("date"), Integer.valueOf(resultSet4.getString("idplace"))));

                            }


                            List<Zakaz> zakazs2 = new LinkedList<Zakaz>();
                            ResultSet resultSet5 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = " + Integer.valueOf(idu) + " and zakaz.status = 'end' and zakaz.idplace=place.idplace");

                            while (resultSet5.next()) {
                                zakazs2.add(new Zakaz(resultSet5.getString("nameplace"), resultSet5.getString("date"), Integer.valueOf(resultSet5.getString("idplace"))));

                            }

                            List<Zakaz> zakazs3 = new LinkedList<Zakaz>();
                            ResultSet resultSet6 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = " + Integer.valueOf(idu) + " and zakaz.status = 'not' and zakaz.idplace=place.idplace");

                            while (resultSet6.next()) {
                                zakazs3.add(new Zakaz(resultSet6.getString("nameplace"), resultSet6.getString("date"), Integer.valueOf(resultSet6.getString("idplace"))));

                            }


                            request.setAttribute("zakaz1", zakazs1);
                            request.setAttribute("zakaz2", zakazs2);
                            request.setAttribute("zakaz3", zakazs3);
                            request.setAttribute("places", places);

                            request.setAttribute("places", places);
                            request.setAttribute("idu", idu);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");

                            if (dispatcher != null) {

                                dispatcher.forward(request, response);

                            }
                        } else {
                            ResultSet resultSet3 = getResultSet("Select * from project.place");

                            List<Place> places = new LinkedList<Place>();

                            while (resultSet3.next()) {
                                if (resultSet3.getString("current").equals("0"))
                                    request.setAttribute("current", resultSet3.getString("nameplace"));
                                places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));

                            }


                            request.setAttribute("places1", places);
                            request.setAttribute("places2", places);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");

                            if (dispatcher != null) {

                                dispatcher.forward(request, response);

                            }
                        }


                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        else
        {
            request.setAttribute("err", "Ошибка ввода данных");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

            if (dispatcher != null) {

                dispatcher.forward(request, response);

            }
        }

       /* if (!request.getParameter("place").equals("")){
            try {
                Insert("insert into project.place (nameplace,current) values('" + request.getParameter("place") + "',0)");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
*/
/*

       List<Place> places = new LinkedList<Place>();

        //TreeMap treeMap = new TreeMap();
       // TreeSet treeSet = new TreeSet();
       // SortedMap sortedMap = new TreeMap();

        try {
            ResultSet resultSet = getResultSet("Select * from project.place");
            while (resultSet.next()) {
                places.add(new Place(resultSet.getString("nameplace"),Integer.valueOf(resultSet.getString("idplace"))));

            }
            request.setAttribute("places",places);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }




        request.setAttribute("dat","WORK");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

        if (dispatcher != null) {

            dispatcher.forward(request, response);

        }*/
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }



    private static void Insert(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = conn.createStatement();
        statement.execute("SET CHARACTER SET 'utf8';");
        statement.execute(query);

    }
    private static ResultSet getResultSet(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

        Statement statement = conn.createStatement();
        statement.execute("SET CHARACTER SET 'utf8';");
        ResultSet result = statement.executeQuery(query);
        return result;

    }

}
