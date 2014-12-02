package tab.os.rest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tab.os.entities.HappyUser;
import tab.os.entities.ShuffleResult;
import tab.os.tools.DBSession;
import tab.os.tools.SantaCipher;
import tab.os.tools.SecretSanta;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by andrey.tesluk on 27.11.2014.
 */
@Path("/")
public class RegistrationRest {

    @GET
    @Path("/regme")
    public Response getPage() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/regme.jsp").build();
    }

    @GET
    @Path("/list")
    public Response getList() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/list.jsp").build();
    }

    @POST
    @Path("/regme")
    public Response upload(@FormParam("invite") String invite, @FormParam("key") String pubKey) {

        if (invite == null || invite.isEmpty() || pubKey == null || pubKey.isEmpty()) {
            return Response.status(400).entity("Bad request").build();
        }

        Session session = DBSession.getSession();

        HappyUser user = (HappyUser) session.load(HappyUser.class, invite);

        if (user == null) {
            return Response.status(400).entity("invalid invite").build();
        }

        Transaction transaction = session.beginTransaction();

        user.setPublicKey(pubKey);
        session.update(user);
        transaction.commit();

        return Response.status(301).header(HttpHeaders.LOCATION, "/list.jsp").build();
    }

    @GET
    @Path("/whoismine")
    public Response getRes() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/whoismine.jsp").build();
    }

    @POST
    @Path("/whoismine")
    public Response whoIsMine(@FormParam("key") String prKey) {
        try {
            Session session = DBSession.getSession();
            List<ShuffleResult> results = session.createCriteria(ShuffleResult.class).list();
            System.out.println("Result size: " + results.size());
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {
                String decrypted = SantaCipher.decrypt(results.get(i).getValue(), prKey);
                System.out.println(i + " " + decrypted);
                res.append(String.format("<p>%d) %s  </p>\n", i, decrypted));
            }
            return Response.ok(res.toString()).build();
        } catch (Exception e) {
            StackTraceElement[] trace = e.getStackTrace();
            StringBuilder builder = new StringBuilder();
            for (StackTraceElement elem : trace) {
                builder.append(elem.toString()).append("<br>\n");
            }
            return Response.status(400).entity("Error: " + e.getMessage() + "<br>\n" + builder.toString()).build();
        }
    }


    @GET
    @Path("/shuffle")
    public Response getShuffler() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/shuffler.jsp").build();
    }

    @POST
    @Path("/shuffle")
    public Response shuffle(@FormParam("pass") String pass) {
        if (!pass.equals("t14v0k3_")) {
            return Response.status(403).entity("Access forbidden").build();
        }
        Session session = DBSession.getSession();
        List<HappyUser> users = session.createQuery("FROM HappyUser E WHERE E.publicKey != NULL").list();

        try {
            List<String> res = SecretSanta.get(users);

            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM ShuffleResult").executeUpdate();

            for (String str : res) {
                session.save(new ShuffleResult(str));
            }
            transaction.commit();

        } catch (Exception e) {
            return Response.status(500).entity("Server error: " + e.getMessage()).build();
        }
        return Response.status(301).header(HttpHeaders.LOCATION, "/whoismine.jsp").build();
    }

}
