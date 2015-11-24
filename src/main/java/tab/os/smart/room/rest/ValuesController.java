package tab.os.smart.room.rest;

import com.google.gson.Gson;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tab.os.db.DBSession;
import tab.os.smart.room.entities.Indication;
import tab.os.smart.room.entities.Tool;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by andrey.tesluk on 28.01.2015.
 */
@Path("/data")
public class ValuesController {

    @GET
    @Path("/tools")
    @Produces("application/json")
    public Response getTools() {
        Session session = DBSession.getSession();
        List<Tool> tools = session.createCriteria(Tool.class).list();
        String res = new Gson().toJson(tools);
        return Response.ok(res).build();
    }

//    @GET
//    @Path("/last")
//    public Response getLastIndication(@QueryParam("id") String id) {
//        if (id == null || id.isEmpty()) {
//            return Response.serverError().entity("Id cannot be blank").build();
//        }
//        Session session = DBSession.getSession();
//        Tool tool;
//        try {
//            tool = (Tool) session.load(Tool.class, id);
//        } catch (ObjectNotFoundException onfe) {
//            return Response.status(Response.Status.NOT_FOUND).entity(String.format("Tool with id '%s' not found", id)).build();
//        }
//
//        List<Indication> inds = tool.getIndications();
//        Optional<Indication> indOpt = inds.stream().sorted(Comparator.comparing(Indication::getTime)).reduce((a, b) -> b);
//        if (!indOpt.isPresent()) {
//            return Response.status(200).entity(String.format("null", tool.getId())).build();
//        }
//        Indication ind = indOpt.get();
//        String res = new Gson().toJson(ind);
//        return Response.ok(res).build();
//    }

    @GET
    @Path("/all")
    public Response getAllIndications(@QueryParam("id") String id) {
        if (id == null || id.isEmpty()) {
            return Response.serverError().entity("Id cannot be blank").build();
        }
        Session session = DBSession.getSession();
        Tool tool;
        try {
            tool = (Tool) session.load(Tool.class, id);
        } catch (ObjectNotFoundException onfe) {
            return Response.status(Response.Status.NOT_FOUND).entity(String.format("Tool with id '%s' not found", id)).build();
        }

        List<Indication> inds = tool.getIndications();

        String res = new Gson().toJson(inds);
        return Response.ok(res).build();
    }

    @POST
    @Path("/add")
    public Response addCurrentValue(@FormParam("id") String toolId, @FormParam("value") Integer value) {
        if (value == null) {
            return Response.serverError().entity("Value cannot be blank").build();
        }
        if (toolId == null || toolId.isEmpty()) {
            return Response.serverError().entity("Id cannot be blank").build();
        }
        Session session = DBSession.getSession();
        Tool tool;
        try {
            tool = (Tool) session.load(Tool.class, toolId);
        } catch (ObjectNotFoundException onfe) {
            return Response.status(400).entity(String.format("Tool with id '%s' not found", toolId)).build();
        }

        Transaction transaction = session.beginTransaction();

        Indication ind = new Indication();

        ind.setTime(new Date());
        ind.setTool(tool);
        ind.setValue(value);
        session.save(ind);

        transaction.commit();

        return Response.status(200).entity("Data saved").build();
    }
}
