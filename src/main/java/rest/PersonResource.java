package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import utils.EMF_Creator;
import facades.PersonFacade;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Path("person")
public class PersonResource {

     private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
     private final PersonFacade personFacade = PersonFacade.getPersonFacadeMethods(EMF);
     private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Hidden
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Operation(summary = "Total persons amount",
            tags = {"Total person count"}
           )

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {

        long count = personFacade.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Operation(summary = "Total by id",
            tags = {"Person by ID"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "The person"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) {
        PersonDTO pdID = personFacade.getPerson(id);
        return Response.ok().entity(GSON.toJson(pdID)).build();
    }


    @Operation(summary = "Person by Phone",
            tags = {"Person by phone"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "Person by phone"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})

    @GET
    @Path("phone/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByPhone(@PathParam("id") int id) {
        PersonDTO pdID = personFacade.getPersonByPhone(id);
        return Response.ok().entity(GSON.toJson(pdID)).build();
    }


    @Operation(summary = "All persons",
            tags = {"All persons"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "All persons"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        PersonsDTO persons = personFacade.getAllPersons();
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }


    @Operation(summary = "All persons with hobby",
            tags = {"All persons with hobby"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "All persons said hobby"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})
    @GET
    @Path("allpwh/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersonsWithHobby(@PathParam("name") String name) {
        PersonsDTO persons = personFacade.getAllPersonsWithHobby(name);
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }


    @Operation(summary = "All persons in city",
            tags = {"All persons in city"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "All persons in city"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})
    @GET
    @Path("allpic/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersonsInCity(@PathParam("id") int id) {
        PersonsDTO persons = personFacade.getAllPersonsLivingInCity(id);
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }

    @Operation(summary = "Create person",
            tags = {"Create a new person"},
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "200", description = "Create person"),
                    @ApiResponse(responseCode = "400", description = "Entity not found")})

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPerson(String p) throws Exception {
        PersonDTO personDTO = GSON.fromJson(p, PersonDTO.class);
        PersonDTO result = personFacade.addPerson(personDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

    @Operation(summary = "Delete person by ID",
            tags = {"Delete"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Person deleted"),
                    @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            }
    )

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) {
        PersonDTO result = personFacade.deletePerson(id);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String p) throws Exception
    {
        PersonDTO personDTO = GSON.fromJson(p, PersonDTO.class);
        personDTO.setDto_id(id);
        PersonDTO result = personFacade.updatePerson(personDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

}
