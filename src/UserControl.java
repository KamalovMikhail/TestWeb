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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikhail on 30.03.15.
 */
@WebServlet(name = "UserControl")
public class UserControl extends HttpServlet  implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usid= request.getParameter("idu");
        String placeId = request.getParameter("places");
        System.out.println(usid+" "+placeId);

        try {
            Insert("insert into project.zakaz (iduser,date,idplace,status) values ("+Integer.valueOf(usid)+",curdate(),"+Integer.valueOf(placeId)+",'action')");


            ResultSet resultSet3 = getResultSet("Select * from project.place");

            List<Place> places = new LinkedList<Place>();

            while (resultSet3.next()) {
                places.add(new Place(resultSet3.getString("nameplace"),Integer.valueOf(resultSet3.getString("idplace"))));

            }

            List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
            ResultSet resultSet4 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = "+Integer.valueOf(usid)+" and zakaz.status = 'action' and zakaz.idplace=place.idplace");

            while (resultSet4.next()) {
                zakazs1.add(new Zakaz(resultSet4.getString("nameplace"),resultSet4.getString("date"),Integer.valueOf(resultSet4.getString("idplace"))));

            }


            List<Zakaz> zakazs2 = new LinkedList<Zakaz>();
            ResultSet resultSet5 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = "+Integer.valueOf(usid)+" and zakaz.status = 'end' and zakaz.idplace=place.idplace");

            while (resultSet5.next()) {
                zakazs2.add(new Zakaz(resultSet5.getString("nameplace"),resultSet5.getString("date"),Integer.valueOf(resultSet5.getString("idplace"))));

            }

            List<Zakaz> zakazs3 = new LinkedList<Zakaz>();
            ResultSet resultSet6 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.iduser = "+Integer.valueOf(usid)+" and zakaz.status = 'not' and zakaz.idplace=place.idplace");

            while (resultSet6.next()) {
                zakazs3.add(new Zakaz(resultSet6.getString("nameplace"),resultSet6.getString("date"),Integer.valueOf(resultSet6.getString("idplace"))));

            }


            request.setAttribute("zakaz1",zakazs1);
            request.setAttribute("zakaz2",zakazs2);
            request.setAttribute("zakaz3",zakazs3);
            request.setAttribute("places",places);

            request.setAttribute("places",places);
            request.setAttribute("idu",usid);
            RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");

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
