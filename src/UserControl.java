import control.connect.Connect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mikhail on 30.03.15.
 */
@WebServlet(name = "UserControl")
public class UserControl extends HttpServlet  implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usid= request.getParameter("idu");
        String placeId = request.getParameter("places");


        try {
            Insert("insert into project.zakaz (iduser,date,idplace,status) values ("+Integer.valueOf(usid)+",curdate(),"+Integer.valueOf(placeId)+",'action')");
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
}
