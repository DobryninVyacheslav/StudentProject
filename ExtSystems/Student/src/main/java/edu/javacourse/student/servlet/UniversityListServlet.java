package edu.javacourse.student.servlet;

import edu.javacourse.student.domain.University;
import edu.javacourse.student.service.UniversityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UniversityListServlet", urlPatterns = {"/universityList"})
public class UniversityListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        WebApplicationContext webContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        UniversityService service = webContext.getBean(UniversityService.class);
        List<University> universities = service.findUniversities();
        universities.forEach(u -> System.out.println(
                u.getUniversityId() + ":" + u.getUniversityName() + ":" + u.getFaculties().size()));

        servletContext.getRequestDispatcher("/universityList.jsp").forward(req, resp);
    }
}
