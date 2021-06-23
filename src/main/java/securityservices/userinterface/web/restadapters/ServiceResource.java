package securityservices.userinterface.web.restadapters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import securityservices.core.service.appservices.AddServicesController;
import securityservices.core.service.appservices.DeleteServicesController;
import securityservices.core.service.appservices.GetAllServicesController;
import securityservices.core.service.appservices.GetByIdServicesController;
import securityservices.core.service.appservices.JsonServiceSerializer;
import securityservices.core.service.appservices.ServiceListSerializer;
import securityservices.core.service.appservices.UpdateServicesController;
import securityservices.core.service.appservices.UseCaseServiceController;
import securityservices.core.service.domain.model.Service;
import securityservices.core.service.domain.services.ServiceDTO;
import securityservices.core.service.domain.services.ServiceMapper;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.shared.responses.ResultRequest;

@Path("service")
public class ServiceResource {

    @Context
    private UseCaseServiceController useCaseController = new UseCaseServiceController();

    public ServiceResource() {
    }

    //PUT(update())
    @PUT
    @Path("/{serviceid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateServiceByID(@PathParam("serviceid") String id, InputStream bodyParams) {
        UpdateServicesController updateServicesController = new UpdateServicesController();
        String message = "", line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(bodyParams));
            while ((line = reader.readLine()) != null) {
                message += line.trim();
            }
            Json jobj = JsonObjectFactory.getInstance();
            jobj.set(message);

            String code = jobj.get("code");
            String name = jobj.get("name");
            String type = jobj.get("type");
            String maker = jobj.get("maker");
            String description = jobj.get("description");
            Double price = Double.valueOf(jobj.get("price"));
            String periodicity = jobj.get("periodicity");
            String conditions = jobj.get("conditions");
            String startdate = jobj.get("startdate");
            String finishdate = jobj.get("finishdate");

            ResultRequest<Service> serviceRequest = Service.getInstance(code, name, type, maker, description, price,
                    periodicity, conditions, startdate, finishdate);
            Service service = serviceRequest.getValue();
            ServiceDTO sdto = ServiceMapper.dtoFromComponent(service);
            ResultRequest<String> request = updateServicesController.update(sdto);
        } catch (IOException ex) {
            message = "Error Reading...";
        }

        return Response.ok("{\"Result\":\"Actualizado service id: "+id+"\"}", MediaType.APPLICATION_JSON).build();
    }

    //POST(add())
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addServices(InputStream bodyParams) {
        AddServicesController addServicesController = new AddServicesController();
        String body = "", line;
        try { //Con FileAdapter ya usamos estas classes para leer flujos de datos y su conversion a texto
            BufferedReader reader = new BufferedReader(new InputStreamReader(bodyParams));
            while ((line = reader.readLine()) != null) { // lectura del body eliminando espacios
                body += line.trim();
            }
            Json jobj = JsonObjectFactory.getInstance();
            jobj.set(body);

            String code = jobj.get("code");
            String name = jobj.get("name");
            String type = jobj.get("type");
            String maker = jobj.get("maker");
            String description = jobj.get("description");
            Double price = Double.valueOf(jobj.get("price"));
            String periodicity = jobj.get("periodicity");
            String conditions = jobj.get("conditions");
            String startdate = jobj.get("startdate");
            String finishdate = jobj.get("finishdate");

            ResultRequest<Service> serviceRequest = Service.getInstance(code, name, type, maker, description, price,
                    periodicity, conditions, startdate, finishdate);
            Service service = serviceRequest.getValue();
            ServiceDTO sdto = ServiceMapper.dtoFromComponent(service);
            ResultRequest<String> request = addServicesController.add(sdto);
        } catch (IOException ex) {
            body = "Error Reading...";
        }
        return Response.ok("{\"Result\":\"Insertado service\"}", MediaType.APPLICATION_JSON).build();
    }

    //DELETE(deletebyid())
    @DELETE
    @Path("/{serviceid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEquipmentByID(@PathParam("serviceid") String id) {
        DeleteServicesController deleteByIdServicesController = new DeleteServicesController();
        ResultRequest<String> request = deleteByIdServicesController.deleteByID(id);
        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }
        return Response.ok("{\"Result\":\"Borrado service " + id + "\"}",
                MediaType.APPLICATION_JSON).build();
    }

    //GET BY ID(getbyid())
    @GET
    @Path("/{serviceid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServiceByID(@PathParam("serviceid") String id) {
        GetByIdServicesController getByIdServicesController = new GetByIdServicesController();
        ResultRequest<ServiceDTO> request = getByIdServicesController.getByID(id);
        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }
        JsonServiceSerializer jcSerializer = new JsonServiceSerializer();
        ServiceDTO Service = request.getValue();
        ResultRequest<String> serviceSerialized = jcSerializer.serialize(Service);
        return Response.ok(serviceSerialized.getValue(), MediaType.APPLICATION_JSON).build();
    }

    //GET ALL(getall())
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllServices() {
        //Utilitza 2 controladors de AppServices, un per obtenir els serveis i un altre per serialitzar-los
        GetAllServicesController allServicesController = new GetAllServicesController();
        ResultRequest<List<ServiceDTO>> request = allServicesController.getAll();

        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }

        return Response.ok(ServiceListSerializer.makeJsonResult(request.getValue()),
                MediaType.APPLICATION_JSON).build();
    }
    /*@GET
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
    }*/
}
