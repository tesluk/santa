package tab.os.yana.santa.rest;

import tab.os.yana.santa.entity.RadikLog;
import tab.os.yana.santa.entity.ShufflePair;
import tab.os.yana.santa.entity.UserWithBD;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

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
    public Response getUserByInn(@FormParam("inn") String inn) {
        UserWithBD user = UserWithBD.getUserByINN(inn);
        return Response.ok(user == null ? "Anonymous" : user.getName()).build();
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
