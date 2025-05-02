import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.html"); // Not logged in
            return;
        }

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("C101", "Java Programming", "Gayntha"));
        courses.add(new Course("C102", "Web Development", "Jaanaka"));
        courses.add(new Course("C103", "Data Structures", "Anupama"));

        request.setAttribute("username", username);
        request.setAttribute("courses", courses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);

        // TODO: Implement dashboard logic
        // 1. Check if user is logged in (session)
        // 2. Create a list of courses (hardcoded)
        // 3. Store courses in request attribute
        // 4. Forward to dashboard.jsp
    }
}