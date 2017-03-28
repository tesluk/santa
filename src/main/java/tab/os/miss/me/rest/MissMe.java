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

        if (code != null) {
            code = code.toLowerCase();
            switch (code) {
                case "it803":
                    return Response.ok("Great!").build();

                // A
                case "1030":
                    return Response.ok("It's A! Yeah!").build();
                // B
                case "x1F520":
                    return Response.ok("It's B! Cool!").build();
                // C
                case "х44552":
                    return Response.ok("It's C! Good job!").build();
                // D
                case "x44255":
                    return Response.ok("It's D! Awesome!").build();
                // E
                case "х44551":
                    return Response.ok("It's E! You found it!").build();
                // F
                case "01042522":
                    return Response.ok("It's F! The hard one!").build();
                // G
                case "279121":
                    return Response.ok("It's G! Magic!").build();
                // H
                case "278565":
                    return Response.ok("It's H! Good news!").build();
                // I
                case "72142":
                    return Response.ok("It's I! You rock!").build();
            }
        }
        return Response.ok("Try again").build();
    }
}


