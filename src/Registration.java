import control.connect.Connect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by mikhail on 30.03.15.
 */
@WebServlet(name = "Registration")
public class Registration extends HttpServlet  implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String name = request.getParameter("name");
    String login = request.getParameter("login");
    String pass =  request.getParameter("pass");

    String query = "SELECT * FROM project.user where user.login = '"+login+"';";
        try {
            ResultSet resultSet = getResultSet(query);
            while (resultSet.next()) {
              if (request.getParameter("name").equals(null))
              {
                  Insert("insert into project.name (name,login,password,status) values ('" + name + "','" + login + "','" + pass + "',0)");
                  request.setAttribute("err","Вы успешно зарегестрированы");
                  RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

                  if (dispatcher != null) {

                      dispatcher.forward(request, response);

                  }
                  break;
              }
                else
              {
                  request.setAttribute("err","Пользователь с данным логином существует");
                  RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

                  if (dispatcher != null) {

                      dispatcher.forward(request, response);

                  }
                  break;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
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

    private static void Insert(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = conn.createStatement();
        statement.execute(query);

    }
}
