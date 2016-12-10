package tab.os.yana.santa.rest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import tab.os.db.DBSession;
import tab.os.yana.santa.entity.RadikLog;
import tab.os.yana.santa.entity.ShufflePair;
import tab.os.yana.santa.entity.UserWithBD;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by andrey.tesluk on 27.11.2014.
 */
@Path("/")
public class RegistrationRest {

    @GET
    @Path("/whoami")
    public Response getWhoAmIByInn() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/rad/whoami.jsp").build();
    }

    @POST
    @Path("/whoami")
    public Response getUserByInn(@FormParam("inn") String inn, @Context HttpServletRequest req) {
        String log = String.format("Get result for %s, from ip %s", inn, req.getRemoteAddr());
        RadikLog.addMsg(log);

        UserWithBD user = UserWithBD.getUserByINN(inn);
        if(user==null){
            return Response.ok("User not found").build();
        }

        Session session = DBSession.getSession();
        List<ShufflePair> pairs = session.createCriteria(ShufflePair.class).list();
        ShufflePair p = null;
        for(ShufflePair sp : pairs){
            if(sp.getWho().equals(user.getName())){
                p = sp;
                break;
            }
        }

        if(p==null){
            return Response.ok(String.format("%s, your pair not found", user.getName())).build();
        }else{
            return Response.ok(String.format("%s, prepare gift for %s", p.getWho(), p.getTo())).build();
        }
    }

    @POST
    @Path("/shuffle")
    public Response shuffle(@FormParam("password") String pass, @FormParam("generate") String generate) throws URISyntaxException {
        RadikLog.addMsg("Test " + generate);
        if (!pass.equals("t14v0k3")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (generate != null) {
            RadikLog.addMsg("Generation");
            ShufflePair.generate();
        }

        return Response.temporaryRedirect(new URI("/rad/shuffle.jsp")).build();
    }

}
