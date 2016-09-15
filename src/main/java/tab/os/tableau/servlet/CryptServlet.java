package tab.os.tableau.servlet;

import tab.os.tableau.CryptUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author andrew.tesliuk
 */
public class CryptServlet extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.sendRedirect("/tableau/crypt.jsp");
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String exponent = req.getParameter("exponent");
        String modulus = req.getParameter("modulus");
        String password = req.getParameter("password");

        String encrypted = CryptUtils.getEncryptedPass(modulus, exponent, password);

        resp.getWriter().println(encrypted);
    }
}
