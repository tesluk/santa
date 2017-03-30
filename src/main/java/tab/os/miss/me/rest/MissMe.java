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

            if (code.equals("it803")) {
                return Response.ok("Great!").build();
            }

            if(!code.matches("x[0-9a-f]+") && !code.matches("\\d+")){
                return Response.ok("Invalid format. Use natural numbers. Try Again").build();
            }

            switch (code) {
                // A
                case "512":
                case "x200":
                    return Response.ok("It's A (512)! Yeah!").build();
                // B
                case "x1f520":
                case "128288":
                    return Response.ok("It's B (x1F520)! Cool!").build();
                // C
                case "x44552":
                    return Response.ok("It's C (x44552)! Good job!").build();
                // D
                case "x44255":
                case "279125":
                    return Response.ok("It's D (x44255)! Awesome!").build();
                // E
                case "x44551":
                case "279889":
                    return Response.ok("It's E (x44551)! You found it!").build();
                // F
                case "01042522":
                case "01042020":
                case "279890":
                    return Response.ok("It's F (279890)! The hard one!").build();
                // G
                case "279121":
                case "x44251":
                    return Response.ok("It's G (279121)! Magic!").build();
                // H
                case "278565":
                case "x44025":
                    return Response.ok("It's H (278565)! Good news!").build();
                // I
                case "72142":
                case "262146":
                    return Response.ok("It's I (262146)! You rock!").build();
            }
        } return Response.ok("Try again").build();
    }
}


