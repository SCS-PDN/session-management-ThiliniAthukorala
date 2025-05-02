import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    private List<Course> getAvailableCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("C101", "Java Programming", "Gayntha"));
        courses.add(new Course("C102", "Web Development", "Jaanaka"));
        courses.add(new Course("C103", "Data Structures", "Anupama"));
        return courses;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        for (Course course : getAvailableCourses()) {
            if (course.getCourseId().equals(courseId)) {
                boolean alreadyEnrolled = enrolledCourses.stream()
                        .anyMatch(c -> c.getCourseId().equals(courseId));
                if (!alreadyEnrolled) {
                    enrolledCourses.add(course);
                    session.setAttribute("enrolledCourses", enrolledCourses);
                }
                break;
            }
        }

        response.sendRedirect("DashboardServlet");
    }
}
