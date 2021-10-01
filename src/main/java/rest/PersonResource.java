package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource
{

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final PersonFacade personFacade =  PersonFacade.getPersonFacadeMethods(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {

        long count = personFacade.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id){
        PersonDTO pdID = personFacade.getPerson(id);
        return Response.ok().entity(GSON.toJson(pdID)).build();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons(){
        PersonsDTO persons = personFacade.getAllPersons();
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }
    @GET
    @Path("allpwh/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersonsWithHobby(@PathParam("name") String name){
        PersonsDTO persons = personFacade.getAllPersonsWithHobby(name);
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }


    @GET
    @Path("allpic/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersonsInCity(@PathParam("id") int id){
        PersonsDTO persons = personFacade.getAllPersonsLivingInCity(id);
        System.out.println(persons);
        return Response.ok().entity(GSON.toJson(persons)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createPerson(String p)
    {
        PersonDTO personDTO = GSON.fromJson(p, PersonDTO.class);
        PersonDTO result = personFacade.addPerson(personDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id)
    {
        PersonDTO result = personFacade.deletePerson(id);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}
