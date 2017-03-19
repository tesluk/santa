package tab.os.miss.me.rest;

import tab.os.miss.me.Access;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * @author andrew.tesliuk
 */
@Path("/") public class MissMe {

    @GET @Path("/missme") public Response getWhoAmIByInn(@Context HttpServletRequest req) {
        Access.logAccess(req.getRemoteAddr(), null, "GET");
        return Response.status(301).header(HttpHeaders.LOCATION, "/miss/me.jsp").build();
    }

    @POST @Path("/missme")
    public Response getUserByInn(@Context HttpServletRequest req, @FormParam("code") String code) {
        Access.logAccess(req.getRemoteAddr(), code, "POST");
        return Response.ok("Anonymous").build();
    }
}


