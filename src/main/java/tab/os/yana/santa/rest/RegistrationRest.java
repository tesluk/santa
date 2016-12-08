package tab.os.yana.santa.rest;

import tab.os.yana.santa.entity.UserWithBD;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by andrey.tesluk on 27.11.2014.
 */
@Path("/") public class RegistrationRest {

    @GET @Path("/whoami") public Response getWhoAmIByInn() {
        return Response.status(301).header(HttpHeaders.LOCATION, "/rad/whoami.jsp").build();
    }

    @POST @Path("/whoami") public Response getUserByInn(@FormParam("inn") String inn) {
        UserWithBD user = UserWithBD.getUserByINN(inn);
        return Response.ok(user == null ? "Anonymous" : user.getName()).build();
    }
}
