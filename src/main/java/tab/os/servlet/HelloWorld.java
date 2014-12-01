package tab.os.servlet;

import org.hibernate.Session;
import tab.os.entities.User;
import tab.os.tools.DBSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Tab on 25.11.2014.
 */
public class HelloWorld extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = DBSession.getSession();

        User user = new User();
        user.setName("User"+new Random().nextInt(500));
        user.setPassword(new Date().toString());

        session.save(user);

        List<User> users = (List<User>) session.createCriteria(User.class).list();

        resp.getWriter().println("<h1>Hello World!" + new Random().nextInt(5000) + "</h1>");
        resp.getWriter().println("Users : " + users.size());
        System.out.println(users);
    }
}
