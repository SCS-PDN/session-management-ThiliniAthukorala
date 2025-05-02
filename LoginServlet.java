import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("student1", "S20");
        users.put("student2", "S21");
        users.put("admin", "admin1");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (users.containsKey(username) && users.get(username).equals(password)) {

                // Step 3a: Create session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Step 3b: Create cookie
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 30); // 30 minutes
                response.addCookie(cookie);

                // Step 3c: Redirect to dashboard
                response.sendRedirect("DashboardServlet");
            } else {
                // Step 4: Invalid login - redirect to login.html
                response.sendRedirect("login.html");
            }
        }
        // TODO: Implement login logic
        // 1. Get username & password from request
        // 2. Validate credentials (hardcode a few users)
        // 3. If valid:
        //    - Create session
        //    - Store username in cookie
        //    - Redirect to DashboardServlet
        // 4. If invalid, redirect back to login.html
    }
}