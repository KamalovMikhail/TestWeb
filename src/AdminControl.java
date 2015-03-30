import control.connect.Connect;
import control.object.Place;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikhail on 30.03.15.
 */
@WebServlet(name = "AdminControl")
public class AdminControl extends HttpServlet implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String index = request.getParameter("index");


        String place2 = request.getParameter("places2");
        String place1 = request.getParameter("places1");
        String time = request.getParameter("time");
        if (index.equals("2")) {
            try {
                Insert("insert into project.map (place1,place2,time) values (" + Integer.valueOf(place1) + "," + Integer.valueOf(place2) + "," + Integer.valueOf(time) + ")");

                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"),Integer.valueOf(resultSet3.getString("idplace"))));
                    if (resultSet3.getString("current").equals("0"))
                        request.setAttribute("current",resultSet3.getString("nameplace"));
                }


                request.setAttribute("places1",places);
                request.setAttribute("places2",places);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");

                if (dispatcher != null) {

                    dispatcher.forward(request, response);

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



        }else{
            try {
                String place = request.getParameter("place");
                ResultSet resultSet2 = getResultSet("Select max(place.current)+1 as mx from project.place");

                resultSet2.next();
                System.out.println(place);
                Insert("insert into project.place (nameplace,current) values('" + request.getParameter("place") + "',"+resultSet2.getString("mx")+")");

                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"),Integer.valueOf(resultSet3.getString("idplace"))));
                    if (resultSet3.getString("current").equals("0"))
                        request.setAttribute("current",resultSet3.getString("nameplace"));
                }


                request.setAttribute("places1",places);
                request.setAttribute("places2",places);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");

                if (dispatcher != null) {

                    dispatcher.forward(request, response);

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



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private static void Insert(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = conn.createStatement();
        statement.execute(query);

    }
    private static ResultSet getResultSet(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        return result;

    }
}

