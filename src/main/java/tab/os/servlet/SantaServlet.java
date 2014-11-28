package tab.os.servlet;

import tab.os.tools.SantaCipher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrey.tesluk on 28.11.2014.
 */
public class SantaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/whoismine.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String encryptedText = req.getParameter("text");

            String myPrivateKey = req.getParameter("key");

            String[] encryptedRows = encryptedText.split("\n");

            StringBuilder res = new StringBuilder();
            for (int i = 0; i < encryptedRows.length; i++) {
                String decrypted = SantaCipher.decrypt(encryptedRows[i], myPrivateKey);
                res.append(String.format("%d) %s  \n", i, decrypted));
            }
            resp.getWriter().println(res.toString());
        } catch (Exception e) {
//            resp.getWriter().println("Google 241543903");
            resp.getWriter().println(e.getMessage());
        }
    }
}
