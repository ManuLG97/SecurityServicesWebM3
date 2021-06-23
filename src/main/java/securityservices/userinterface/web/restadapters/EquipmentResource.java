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
import javax.ws.rs.core.Response.Status;
import securityservices.core.equipment.appservices.AddEquipmentsController;
import securityservices.core.equipment.appservices.DeleteEquipmentsController;
import securityservices.core.equipment.appservices.EquipmentListSerializer;
import securityservices.core.equipment.appservices.GetAllEquipmentsController;
import securityservices.core.equipment.appservices.GetByIdEquipmentsController;
import securityservices.core.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.equipment.appservices.UpdateEquipmentsController;
import securityservices.core.equipment.appservices.UseCaseEquipmentController;
import securityservices.core.equipment.domain.model.Equipment;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.core.equipment.domain.services.EquipmentMapper;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.shared.responses.ResultRequest;

@Path("equipment")
public class EquipmentResource {

    @Context
    private UseCaseEquipmentController useCaseController = new UseCaseEquipmentController();

    public EquipmentResource() {
    }

    //PUT(update())
    @PUT
    @Path("/{equipmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateServiceByID(@PathParam("equipmentid") String id, InputStream bodyParams) {
        UpdateEquipmentsController updateEquipmentsController = new UpdateEquipmentsController();
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
            Double high = Double.valueOf(jobj.get("high"));
            Double wide = Double.valueOf(jobj.get("wide"));
            Double deep = Double.valueOf(jobj.get("deep"));
            Double weight = Double.valueOf(jobj.get("weight"));
            Boolean fragile = Boolean.valueOf(jobj.get("fragile"));
            String function = jobj.get("function");
            String components = jobj.get("components");
            Integer power = Integer.valueOf(jobj.get("power"));

            ResultRequest<Equipment> equipmentRequest = Equipment.getInstance(code, name, type, maker, description,
                    price, high, wide, deep, weight, fragile, function, components, power);
            
            Equipment equipment = equipmentRequest.getValue();
            EquipmentDTO sdto = EquipmentMapper.dtoFromComponent(equipment);
            ResultRequest<String> request = updateEquipmentsController.update(sdto);
        } catch (IOException ex) {
            message = "Error Reading...";
        }

        return Response.ok("{\"Result\":\"Actualizado equipment id: " + id + "\"}", MediaType.APPLICATION_JSON).build();
    }

    //POST(add())
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEquipments(InputStream bodyParams) {
        AddEquipmentsController addEquipmentsController = new AddEquipmentsController();
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
            Double high = Double.valueOf(jobj.get("high"));
            Double wide = Double.valueOf(jobj.get("wide"));
            Double deep = Double.valueOf(jobj.get("deep"));
            Double weight = Double.valueOf(jobj.get("weight"));
            Boolean fragile = Boolean.valueOf(jobj.get("fragile"));
            String function = jobj.get("function");
            String components = jobj.get("components");
            Integer power = Integer.valueOf(jobj.get("power"));

            ResultRequest<Equipment> equipmentRequest = Equipment.getInstance(code, name, type, maker, description,
                    price, high, wide, deep, weight, fragile, function, components, power);
            Equipment equipment = equipmentRequest.getValue();
            EquipmentDTO sdto = EquipmentMapper.dtoFromComponent(equipment);
            ResultRequest<String> request = addEquipmentsController.add(sdto);
        } catch (IOException ex) {
            body = "Error Reading...";
        }
        return Response.ok("{\"Result\":\"Insertado equipment\"}", MediaType.APPLICATION_JSON).build();
    }

    //DELETE(deletebyid())
    @DELETE
    @Path("/{equipmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEquipmentByID(@PathParam("equipmentid") String id) {
        DeleteEquipmentsController deleteByIdEquipmentsController = new DeleteEquipmentsController();
        ResultRequest<String> request = deleteByIdEquipmentsController.deleteByID(id);
        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }
        return Response.ok("{\"Result\":\"Borrado equipment " + id + "\"}",
                MediaType.APPLICATION_JSON).build();
    }

    /*//GET BY ID(getbyid()) NO FUNCIONA
    @GET
    @Path("/{equipmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEquipmentByID(@PathParam("equipmentid") String id) {
        GetByIdEquipmentsController getByIdEquipmentsController = new GetByIdEquipmentsController();
        ResultRequest<EquipmentDTO> request = getByIdEquipmentsController.getByID(id);
        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }
        JsonEquipmentSerializer jcSerializer = new JsonEquipmentSerializer();
        EquipmentDTO Equipment = request.getValue();
        ResultRequest<String> equipmentSerialized = jcSerializer.serialize(Equipment);
        return Response.ok(equipmentSerialized.getValue(), MediaType.APPLICATION_JSON).build();
    }*/

    //GET ALL(getall())
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEquipments() {
        //Utilitza 2 controladors de AppEquipments, un per obtenir els serveis i un altre per serialitzar-los
        GetAllEquipmentsController allEquipmentsController = new GetAllEquipmentsController();
        ResultRequest<List<EquipmentDTO>> request = allEquipmentsController.getAll();

        if (request.failed()) {
            return Response.status(400, request.getError()).build();
        }

        return Response.ok(EquipmentListSerializer.makeJsonResult(request.getValue()),
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
