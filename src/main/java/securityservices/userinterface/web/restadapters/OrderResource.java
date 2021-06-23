package securityservices.userinterface.web.restadapters;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import securityservices.core.order.appservices.UseCaseOrderController;

@Path("order")
public class OrderResource {

    @Context
    private UriInfo context;
    private UseCaseOrderController useCaseController = new UseCaseOrderController();

    public OrderResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        if (useCaseController.getJsonResource().failed()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        String result = useCaseController.getJsonResource().getValue();
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getXml() {
        if (useCaseController.getXmlResource().failed() == false) {
            String result = useCaseController.getXmlResource().getValue();
            return Response.ok(result, MediaType.APPLICATION_XML).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}
