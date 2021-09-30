package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HobbiesDTO;
import facades.HobbyFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("hobby")
public class HobbyResource {
    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final HobbyFacade hobbyFacade =  HobbyFacade.getHobbyFacadeMethods(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }


    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllHobbies(){
        HobbiesDTO hobbiesDTO = hobbyFacade.getAllHobbies();
        return Response.ok().entity(GSON.toJson(hobbiesDTO)).build();
    }

}
