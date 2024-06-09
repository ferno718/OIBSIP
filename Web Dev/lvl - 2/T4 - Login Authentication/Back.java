import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Back extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/culers";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String cont = req.getParameter("cont");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        String cpass = req.getParameter("cpass");

        if (saveUserToDatabase(name, cont, mail, pass, cpass)) {
            out.println("<Record added successfully");
        } else {
            out.println("Failed to save user data");
        }
    }

    private boolean saveUserToDatabase(String name, String cont, String mail, String pass, String cpass) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO barca (name, cont, mail, pass) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, cont);
                preparedStatement.setString(3, mail);
                preparedStatement.setString(4, pass);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            return false;
        }
    }
}