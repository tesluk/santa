package tab.os.smart.room.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Created by andrey.tesluk on 28.01.2015.
 */
@Path("/echo")
public class EchoRest {

    @GET
    public Response echo(@QueryParam("t") String text) {
        return Response.status(200).entity(text).build();
    }
}
