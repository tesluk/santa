package tab.os.santa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by andrey.tesluk on 27.11.2014.
 */
@Path("/hello")
public class HelloRest {

    @GET
    @Path("/{name}")
    public Response getMsg(@PathParam("name") String name) {
        return Response.status(200).entity("Hello, " + name).build();
    }
}
