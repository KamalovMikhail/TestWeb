import control.algorithm.Dijkstra.Graph;
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
@WebServlet(name = "AdminControl")
public class AdminControl extends HttpServlet implements Connect {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String index = request.getParameter("index");

        String cur = request.getParameter("current");
        String place2 = request.getParameter("places2");
        String place1 = request.getParameter("places1");
        String time = request.getParameter("time");
        if (index.equals("2")) {
            try {
                Insert("insert into project.map (place1,place2,time) values (" + Integer.valueOf(place1) + "," + Integer.valueOf(place2) + "," + Integer.valueOf(time) + ")");

                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                    if (resultSet3.getString("current").equals("0"))
                        request.setAttribute("current", resultSet3.getString("nameplace"));
                }


                request.setAttribute("places1", places);
                request.setAttribute("places2", places);
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
        if (index.equals("1")) {
            try {
                String place = request.getParameter("place");
                ResultSet resultSet2 = getResultSet("Select max(place.current)+1 as mx from project.place");

                resultSet2.next();
                System.out.println(place);
                Insert("insert into project.place (nameplace,current) values('" + request.getParameter("place") + "'," + resultSet2.getString("mx") + ")");

                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                    if (resultSet3.getString("current").equals("0"))
                        request.setAttribute("current", resultSet3.getString("nameplace"));
                }


                request.setAttribute("places1", places);
                request.setAttribute("places2", places);
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
        if (index.equals("3")) {
            try {
                ResultSet resultSet4 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.status = 'action' and zakaz.idplace=place.idplace order by zakaz.date ASC ");

                List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
                while (resultSet4.next()) {
                    zakazs1.add(new Zakaz(resultSet4.getString("nameplace"), resultSet4.getString("date"), Integer.valueOf(resultSet4.getString("idplace"))));

                }
                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                    if (resultSet3.getString("current").equals("0"))
                        request.setAttribute("current", resultSet3.getString("nameplace"));
                }

                request.setAttribute("zakaz1", zakazs1);
                request.setAttribute("places1", places);
                request.setAttribute("places2", places);
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
        if (index.equals("4")) {

            try {
                ResultSet resultSet4 = getResultSet("Select count(*) as co from project.map   ");
                resultSet4.next();
                Graph.Edge[] GRAPH = new Graph.Edge[Integer.valueOf(resultSet4.getString("co"))];



                ResultSet resultSet6 = getResultSet("SELECT (select place.nameplace  from project.place where place.idplace = map.place1) as place1,(select place.nameplace  from project.place where place.idplace = map.place2) as place2,map.time  FROM project.map  ;");

                    int i=0;
                   while (resultSet6.next()) {
                        GRAPH[i]= new Graph.Edge(resultSet6.getString("place1"), resultSet6.getString("place2"), Integer.valueOf(resultSet6.getString("time")));
                     i++;
                   }



                ResultSet resultSet5 = getResultSet("Select *  from project.place  where place.current = 0  ");
                resultSet5.next();

                ResultSet resultSet7 = getResultSet("Select *  from project.place  where place.idplace = (Select idplace  from project.zakaz  where zakaz.idzakaz = (Select min(idzakaz)  from project.zakaz  where zakaz.status = 'action' )) ");
                resultSet7.next();

                final String START = resultSet5.getString("nameplace");
                final String END = resultSet7.getString("nameplace");

                Graph g = new Graph(GRAPH);
                g.dijkstra(START);
                String s = g.printPath(END);
                System.out.println();
                System.out.println(s+"!!!!!");

                Graph.path="";

                ResultSet resultSet2 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.status = 'action' and zakaz.idplace=place.idplace order by zakaz.date ASC ");

                List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
                while (resultSet2.next()) {
                    zakazs1.add(new Zakaz(resultSet2.getString("nameplace"), resultSet2.getString("date"), Integer.valueOf(resultSet2.getString("idplace"))));

                }
                ResultSet resultSet3 = getResultSet("Select * from project.place");

                List<Place> places = new LinkedList<Place>();

                while (resultSet3.next()) {
                    places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                       if (resultSet3.getString("current").equals("0"))
                           request.setAttribute("current", resultSet3.getString("nameplace"));
                }

                request.setAttribute("way", s);
                request.setAttribute("zakaz1", zakazs1);
                request.setAttribute("places1", places);
                request.setAttribute("places2", places);
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
        if (index.equals("5")) {
            try {
                if(!request.getParameter("way").equals("Невозможно добраться")) {

                    ResultSet resultSet7 = getResultSet("Select *  from project.place  where place.idplace = (Select idplace  from project.zakaz  where zakaz.idzakaz = (Select min(idzakaz)  from project.zakaz  where zakaz.status = 'action' ))");
                    resultSet7.next();
                    Update("Update project.zakaz set status='end' where zakaz.idplace = " + Integer.valueOf(resultSet7.getString("idplace")) + "");

                    ResultSet resultSet5 = getResultSet("Select *  from project.place  where place.current = 0  ");
                    resultSet5.next();
                    Update("Update project.place set current=1 where place.idplace = " + Integer.valueOf(resultSet5.getString("idplace")) + "");

                    Update("Update project.place set current=0 where place.idplace = " + Integer.valueOf(resultSet7.getString("idplace")) + "");


                    Insert("insert into project.shortWay (start,end,string,time) values (" + Integer.valueOf(resultSet5.getString("idplace")) + "," + Integer.valueOf(resultSet7.getString("idplace")) + ",'" + request.getParameter("way") + "',5);");

                    ResultSet resultSet2 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.status = 'action' and zakaz.idplace=place.idplace order by zakaz.date ASC ");

                    List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
                    while (resultSet2.next()) {
                        zakazs1.add(new Zakaz(resultSet2.getString("nameplace"), resultSet2.getString("date"), Integer.valueOf(resultSet2.getString("idplace"))));

                    }
                    ResultSet resultSet3 = getResultSet("Select * from project.place");

                    List<Place> places = new LinkedList<Place>();

                    while (resultSet3.next()) {
                        places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                        if (resultSet3.getString("current").equals("0"))
                            request.setAttribute("current", resultSet3.getString("nameplace"));
                    }

                    request.setAttribute("way", "Доставка осуществлена");
                    request.setAttribute("zakaz1", zakazs1);
                    request.setAttribute("places1", places);
                    request.setAttribute("places2", places);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");

                    if (dispatcher != null) {

                        dispatcher.forward(request, response);

                    }

                }
                else{

                    ResultSet resultSet7 = getResultSet("Select *  from project.place  where place.idplace = (Select idplace  from project.zakaz  where zakaz.idzakaz = (Select min(idzakaz)  from project.zakaz  where zakaz.status = 'action' ))");
                    resultSet7.next();
                    Update("Update project.zakaz set status='not' where zakaz.idplace = " + Integer.valueOf(resultSet7.getString("idplace")) + "");


                    ResultSet resultSet2 = getResultSet("Select place.nameplace, zakaz.date,zakaz.idplace from project.zakaz,project.place where zakaz.status = 'action' and zakaz.idplace=place.idplace order by zakaz.date ASC ");

                    List<Zakaz> zakazs1 = new LinkedList<Zakaz>();
                    while (resultSet2.next()) {
                        zakazs1.add(new Zakaz(resultSet2.getString("nameplace"), resultSet2.getString("date"), Integer.valueOf(resultSet2.getString("idplace"))));

                    }
                    ResultSet resultSet3 = getResultSet("Select * from project.place");

                    List<Place> places = new LinkedList<Place>();

                    while (resultSet3.next()) {
                        places.add(new Place(resultSet3.getString("nameplace"), Integer.valueOf(resultSet3.getString("idplace"))));
                        if (resultSet3.getString("current").equals("0"))
                            request.setAttribute("current", resultSet3.getString("nameplace"));
                    }

                    request.setAttribute("way", "Доставка невозможна");
                    request.setAttribute("zakaz1", zakazs1);
                    request.setAttribute("places1", places);
                    request.setAttribute("places2", places);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");

                    if (dispatcher != null) {

                        dispatcher.forward(request, response);

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static void Update(String query) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);

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

